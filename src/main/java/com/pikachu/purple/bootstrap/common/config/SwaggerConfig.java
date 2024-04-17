package com.pikachu.purple.bootstrap.common.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
    info = @Info(title = "PURPLE",
        description = "Purple의 API 문서입니다.",
        version = "v1"),
    servers = @Server(url = "http://localhost:8080/", description = "dev-server")
)
@Configuration
public class SwaggerConfig {
    @Bean
    public GroupedOpenApi allOpenApi() {
        String[] paths = {"/perpicks/**"};

        return GroupedOpenApi
            .builder()
            .group("전체 API")
            .pathsToMatch(paths)
            .build();
    }
}
