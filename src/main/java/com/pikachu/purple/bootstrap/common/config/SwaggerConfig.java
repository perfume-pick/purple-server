package com.pikachu.purple.bootstrap.common.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi allOpenApi() {
        return GroupedOpenApi.builder()
            .group("전체 API")
            .pathsToMatch("/perpicks/**")
            .build();
    }

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
            .components(securityComponents())
            .info(new Info().title("PERPICKS")
                .description("Perpicks의 API 문서입니다.")
                .version("v1"));
    }

    private Components securityComponents() {
        return new Components()
            .addSecuritySchemes(
                "JwtToken",
                new SecurityScheme()
                    .type(SecurityScheme.Type.HTTP)
                    .scheme("bearer")
                    .bearerFormat("JWT")
            );
    }

}
