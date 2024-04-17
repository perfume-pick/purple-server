package com.pikachu.purple;

import com.pikachu.purple.application.common.properties.KakaoSocialLoginProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(KakaoSocialLoginProperties.class)
public class PurpleApplication {

    public static void main(String[] args) {
        SpringApplication.run(PurpleApplication.class, args);
    }

}
