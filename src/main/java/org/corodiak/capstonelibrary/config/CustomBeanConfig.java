package org.corodiak.capstonelibrary.config;

import java.io.IOException;

import org.corodiak.capstonelibrary.util.AuthenticationCodeGenerator;
import org.corodiak.capstonelibrary.util.CodeGenerator;
import org.corodiak.capstonelibrary.util.NicknameGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomBeanConfig {

	@Bean
	public NicknameGenerator nicknameGenerator() throws IOException {
		return new NicknameGenerator();
	}

	@Bean
	public AuthenticationCodeGenerator authenticationCodeGenerator() {
		return new AuthenticationCodeGenerator();
	}

	@Bean
	public CodeGenerator codeGenerator() {
		return new CodeGenerator();
	}
}
