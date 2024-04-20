package com.pikachu.purple.bootstrap.common.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebMvcConfig implements WebMvcConfigurer {

    @Value("${uri.client}")
    private String clientUri;

    @Value("${uri.server}")
    private String serverUri;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry
            .addMapping("/perpicks")
            .allowedHeaders("*")
            .allowedOrigins(clientUri, serverUri, "http://localhost:3000")
            .allowedMethods("*");
    }

}
