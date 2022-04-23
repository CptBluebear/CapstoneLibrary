package org.corodiak.capstonelibrary.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.OAS_30)
			.useDefaultResponseMessages(false)
			.select()
			.apis(RequestHandlerSelectors.basePackage("org.corodiak.capstonelibrary.controller"))
			.build()
			.apiInfo(apiInfo());
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
			.title("CapstoneLibrary Swagger")
			.description("KGU 22-1 캡스톤 프로젝트")
			.build();
	}

}
