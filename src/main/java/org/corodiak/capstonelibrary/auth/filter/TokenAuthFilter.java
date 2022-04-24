package org.corodiak.capstonelibrary.auth.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.corodiak.capstonelibrary.auth.jwt.AuthToken;
import org.corodiak.capstonelibrary.auth.jwt.AuthTokenProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class TokenAuthFilter extends OncePerRequestFilter {

	private final AuthTokenProvider authTokenProvider;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
		FilterChain filterChain) throws ServletException, IOException {

		String token = request.getHeader("Authorization");
		log.info("token data : {}", token);
		AuthToken authToken = authTokenProvider.convertToken(token);

		if (authToken.validate()) {
			log.info("token validate");
			Authentication authentication = authTokenProvider.getAuthentication(authToken);
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}

		filterChain.doFilter(request, response);
	}
}
