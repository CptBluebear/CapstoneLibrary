package org.corodiak.capstonelibrary.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Value("${resource.image.realpath}")
	private String imageRealPath;

	@Value("${resource.image.path}")
	private String imageUrlPath;

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		System.out.println(imageRealPath);
		System.out.println(imageUrlPath);
		registry.addResourceHandler(imageUrlPath + "/**").addResourceLocations("file://" + imageRealPath);
	}
}
