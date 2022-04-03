package org.corodiak.capstonelibrary.auth.factory;

import java.util.Map;

import org.corodiak.capstonelibrary.type.dto.GoogleOAuthUserInfo;
import org.corodiak.capstonelibrary.type.dto.OAuthUserInfo;
import org.corodiak.capstonelibrary.type.etc.OAuthProvider;

public class OAuth2UserInfoFactory {

	public static OAuthUserInfo of(OAuthProvider oAuthProvider, Map<String, Object> attributes) {
		switch (oAuthProvider) {
			case GOOGLE:
				return new GoogleOAuthUserInfo(attributes);
			default:
				throw new IllegalArgumentException("OAuthProvider Not Excepted!!");
		}
	}

}
