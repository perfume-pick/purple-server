package com.pikachu.purple.bootstrap.auth.api;

import com.pikachu.purple.bootstrap.auth.dto.request.EmailVerificationCodeRequest;
import com.pikachu.purple.bootstrap.auth.dto.request.EmailVerificationRequest;
import com.pikachu.purple.bootstrap.auth.dto.response.SocialLoginTryResponse;
import com.pikachu.purple.domain.user.enums.SocialLoginProvider;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Tag(name = "Auth", description = "Auth API")
@RequestMapping(value = "/perpicks/auth", produces = "application/json")
public interface AuthApi {

    @Operation(summary = "Generate Login Url for Social Login")
    @PostMapping("/login-try/{provider}")
    @ResponseStatus(HttpStatus.OK)
    SocialLoginTryResponse loginTry(
        @PathVariable("provider") SocialLoginProvider provider
    );

    @Operation(summary = "회원가입시 이메일 인증코드 전송")
    @ApiResponses(
        value = {
            @ApiResponse(
                responseCode = "204", description = "이메일 인증코드 전송 성공"
            ),
            @ApiResponse(
                responseCode = "409", description = "U001: 이미 회원가입을 완료한 이메일입니다."
            ),
            @ApiResponse(
                responseCode = "400", description = "U002: 이메일 형식이 유효하지 않습니다."
            )
        }
    )
    @PostMapping("/verify-code/send")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void send(
        @RequestBody EmailVerificationRequest request
    );

    @Operation(summary = "회원가입시 이메일 인증코드 확인")
    @ApiResponses(
        value = {
            @ApiResponse(
                responseCode = "204", description = "이메일 인증코드 확인 성공"
            ),
            @ApiResponse(
                responseCode = "400", description = "U003: 인증코드가 유효하지 않습니다."
            )
        }
    )
    @PostMapping("/verify-code/confirm")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void confirm(@RequestBody EmailVerificationCodeRequest request);

}
