package org.corodiak.capstonelibrary.config;

import org.corodiak.capstonelibrary.auth.jwt.AuthTokenProvider;
import org.corodiak.capstonelibrary.auth.jwt.AuthTokenProviderImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtConfig {

	@Value("${jwt.secret-key}")
	private String secretKey;

	@Bean
	public AuthTokenProvider authTokenProvider() {
		return new AuthTokenProviderImpl(secretKey);
	}

}
