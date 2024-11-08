package com.pikachu.purple.bootstrap.auth.api;

import com.pikachu.purple.bootstrap.auth.dto.request.RefreshJwtTokenRequest;
import com.pikachu.purple.bootstrap.auth.dto.response.RefreshJwtTokenResponse;
import com.pikachu.purple.bootstrap.auth.dto.response.SocialLoginResponse;
import com.pikachu.purple.bootstrap.auth.dto.response.SocialLoginTryResponse;
import com.pikachu.purple.bootstrap.common.dto.SuccessResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.net.URISyntaxException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

@Tag(name = "Auth", description = "Auth API")
@RequestMapping(value = "/perpicks/auth", produces = "application/json")
public interface AuthApi {

    @Operation(summary = "소셜 로그인 URI 발급")
    @PostMapping("/login-try")
    @ResponseStatus(HttpStatus.OK)
    SuccessResponse<SocialLoginTryResponse> socialLoginTry() throws URISyntaxException;

    @Operation(summary = "소셜 로그인")
    @GetMapping("/login/kakao")
    @ResponseStatus(HttpStatus.OK)
    SuccessResponse<SocialLoginResponse> socialLogin(
        @RequestParam String code
    );

    @Operation(summary = "Jwt Token Refresh API")
    @PostMapping("/refresh")
    @ResponseStatus(HttpStatus.OK)
    SuccessResponse<RefreshJwtTokenResponse> refreshJwtToken(
        @RequestBody RefreshJwtTokenRequest request
    );

}
