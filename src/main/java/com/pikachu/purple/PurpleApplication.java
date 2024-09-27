package com.pikachu.purple;

import com.pikachu.purple.application.common.properties.JwtTokenProperties;
import com.pikachu.purple.application.common.properties.KakaoSocialLoginProperties;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

@OpenAPIDefinition(
    servers = {
        @Server(url="/", description = "Default Server url")
    }
)

@SpringBootApplication
@EnableScheduling
@EnableJpaAuditing
@EnableFeignClients
@EnableConfigurationProperties({KakaoSocialLoginProperties.class, JwtTokenProperties.class})
public class PurpleApplication {

    public static void main(String[] args) {
        SpringApplication.run(PurpleApplication.class, args);
    }

}
