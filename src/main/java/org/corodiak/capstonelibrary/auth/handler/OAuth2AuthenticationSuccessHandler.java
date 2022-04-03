package org.corodiak.capstonelibrary.auth.handler;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.corodiak.capstonelibrary.auth.jwt.AuthToken;
import org.corodiak.capstonelibrary.auth.jwt.AuthTokenProvider;
import org.corodiak.capstonelibrary.type.dto.ResponseModel;
import org.corodiak.capstonelibrary.type.dto.UserPrincipal;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class OAuth2AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

	private final AuthTokenProvider authTokenProvider;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
		Authentication authentication) throws IOException, ServletException {
		UserPrincipal userPrincipal = (UserPrincipal)authentication.getPrincipal();

		String role = authentication.getAuthorities().stream()
			.map(a -> ((GrantedAuthority)a).getAuthority())
			.collect(Collectors.joining("|"));
		System.out.println(role);

		Date expiry = new Date();
		expiry.setTime(expiry.getTime() + (1000L * 60L * 60L * 24L) );
		AuthToken authToken = authTokenProvider.createToken(Long.toString(userPrincipal.getUserId()), role, expiry);
		System.out.println(authToken.getToken());
		ResponseModel responseModel = new ResponseModel();
		responseModel.setHttpStatus(HttpStatus.OK);
		responseModel.setMessage("Authorization Token Issued.");
		responseModel.addData("token", authToken.getToken());
		response.setHeader("Authorization", authToken.getToken());
		OutputStream outputStream = response.getOutputStream();
		outputStream.write(responseModel.toJson().getBytes());
	}
}
