package com.pikachu.purple.bootstrap.auth.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class EmailVerificationCodeRequest {

    @NotBlank
    private final String email;

    @NotBlank
    private final String verificationCode;

}
