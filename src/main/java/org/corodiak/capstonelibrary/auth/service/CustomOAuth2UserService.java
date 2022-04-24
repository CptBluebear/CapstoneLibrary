package org.corodiak.capstonelibrary.auth.service;

import java.util.Optional;

import org.corodiak.capstonelibrary.auth.factory.OAuth2UserInfoFactory;
import org.corodiak.capstonelibrary.repository.OAuthUserRepository;
import org.corodiak.capstonelibrary.repository.UserRepository;
import org.corodiak.capstonelibrary.type.dto.OAuthUserInfo;
import org.corodiak.capstonelibrary.type.dto.UserPrincipal;
import org.corodiak.capstonelibrary.type.entity.OAuthUser;
import org.corodiak.capstonelibrary.type.entity.User;
import org.corodiak.capstonelibrary.type.etc.OAuthProvider;
import org.corodiak.capstonelibrary.type.etc.Role;
import org.corodiak.capstonelibrary.util.NicknameGenerator;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

	private final OAuthUserRepository oAuthUserRepository;
	private final UserRepository userRepository;
	private final NicknameGenerator nicknameGenerator;

	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

		OAuth2User oAuth2User = super.loadUser(userRequest);
		System.out.println(oAuth2User);

		return process(userRequest, oAuth2User);
	}

	private OAuth2User process(OAuth2UserRequest userRequest, OAuth2User oAuth2User) {
		OAuthProvider oAuthProvider = OAuthProvider.valueOf(
			userRequest.getClientRegistration().getClientName().toUpperCase());
		OAuthUserInfo oAuth2UserInfo = OAuth2UserInfoFactory.of(oAuthProvider, oAuth2User.getAttributes());
		OAuth2User user = saveOrUpDate(oAuth2UserInfo);

		return user;
	}

	private OAuth2User saveOrUpDate(OAuthUserInfo oAuth2UserInfo) {
		System.out.println(oAuth2UserInfo);
		System.out.println(oAuth2UserInfo.getId());
		Optional<OAuthUser> savedUser;
		savedUser = oAuthUserRepository.findByProviderUserIdAndOap(
			oAuth2UserInfo.getId(),
			OAuthProvider.valueOf(oAuth2UserInfo.getOAuthProviderName().toUpperCase())
		);
		User user;
		OAuthUser oAuthUser;
		if(savedUser.isPresent()) {
			oAuthUser = savedUser.get();
			user = oAuthUser.getUser();
		} else {
			user = User.builder()
				.nickname(nicknameGenerator.generate())
				.role(Role.USER)
				.build();
			userRepository.save(user);

			oAuthUser = OAuthUser.builder()
				.providerUserId(oAuth2UserInfo.getId())
				.email(oAuth2UserInfo.getEmail())
				.name(oAuth2UserInfo.getName())
				.oap(OAuthProvider.valueOf(oAuth2UserInfo.getOAuthProviderName().toUpperCase()))
				.user(user)
				.build();
		}
		oAuthUserRepository.save(oAuthUser);
		return UserPrincipal.create(user);
	}
}
