package com.pikachu.purple;

import com.pikachu.purple.application.common.properties.JwtTokenProperties;
import com.pikachu.purple.application.common.properties.KakaoSocialLoginProperties;
import com.pikachu.purple.infrastructure.client.auth.kakao.util.KakaoSocialLoginClient;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;

@OpenAPIDefinition(
    servers = {
        @Server(url="/", description = "Default Server url")
    }
)

@SpringBootApplication
@EnableConfigurationProperties({KakaoSocialLoginProperties.class, JwtTokenProperties.class})
@EnableFeignClients
    public class PurpleApplication {

    public static void main(String[] args) {
        SpringApplication.run(PurpleApplication.class, args);
    }

}
