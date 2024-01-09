package edu.dufe.student.registry.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class CORSConfiguration extends WebMvcConfigurationSupport {
	@Value("${allow.origin}")
	private String allowOrigins;
	@Value("${jwt.header.user.name}")
	private String headerUserName;
	@Value("${jwt.header.token.header.name}")
	private String tokenHeaderResponse;
	
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedMethods("*")
                .allowedOrigins(allowOrigins)
                .allowedHeaders("*")
                .allowCredentials(true)
                .exposedHeaders(headerUserName,tokenHeaderResponse);
        super.addCorsMappings(registry);
    }
}
