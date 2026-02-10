package com.employeedatamanagement.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Web configuration class for defining global MVC-related settings.
 * <p>
 * This configuration is mainly used to configure Cross-Origin Resource Sharing (CORS)
 * to allow frontend applications (such as Angular running on localhost:4200)
 * to communicate with the backend APIs.
 * </p>
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

	/**
	 * Configures Cross-Origin Resource Sharing (CORS) mappings for the application.
	 * <p>
	 * This method allows HTTP requests from the specified frontend origin
	 * and permits common HTTP methods and headers.
	 * </p>
	 *
	 * @param registry the CORS registry used to configure allowed origins, methods, and headers
	 */
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedOrigins("http://localhost:4200")
				.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
				.allowCredentials(true)
				.allowedHeaders("*");
	}

}