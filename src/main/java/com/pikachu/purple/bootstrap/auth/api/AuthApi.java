package com.pikachu.purple.bootstrap.auth.api;

import com.auth0.jwk.JwkException;
import com.pikachu.purple.bootstrap.auth.dto.request.RefreshJwtTokenRequest;
import com.pikachu.purple.bootstrap.auth.dto.response.RefreshJwtTokenResponse;
import com.pikachu.purple.bootstrap.auth.dto.response.SocialLoginTryResponse;
import com.pikachu.purple.domain.user.enums.SocialLoginProvider;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URISyntaxException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

@Tag(name = "Auth", description = "Auth API")
@RequestMapping(value = "/perpicks/auth", produces = "application/json")
public interface AuthApi {

    @Operation(summary = "소셜 로그인 URI 발급")
    @PostMapping("/login-try/{provider}")
    @ResponseStatus(HttpStatus.OK)
    SocialLoginTryResponse socialLoginTry(
        @PathVariable("provider") SocialLoginProvider provider
    );

    @Operation(summary = "소셜 로그인")
    @PostMapping("/login/{provider}")
    @ResponseStatus(HttpStatus.OK)
    void socialLogin(
        @PathVariable("provider") SocialLoginProvider provider,
        @RequestParam String code,
        HttpServletResponse response
    ) throws IOException, JwkException, URISyntaxException;

    @Operation(summary = "Jwt Token Refresh API")
    @PostMapping("/refresh")
    @ResponseStatus(HttpStatus.OK)
    RefreshJwtTokenResponse refreshJwtToken(
        @RequestBody RefreshJwtTokenRequest request
    );

}
