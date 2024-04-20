package com.pikachu.purple.bootstrap.common.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Value("${api.server-uri}")
    private String serverUrl;

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
            .info(new Info().title("PERPICKS")
                .description("Perpicks의 API 문서입니다.")
                .version("v1"))
            .addServersItem(new Server().url(serverUrl).description("dev-server"));
    }

}
