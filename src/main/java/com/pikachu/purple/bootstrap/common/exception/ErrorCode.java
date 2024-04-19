package com.pikachu.purple.bootstrap.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    // Common
    INTERNAL_SERVER_ERROR(
        500,
        "C001",
        "서버에 오류가 발생하였습니다."
    ),

    // User
    EMAIL_ALREADY_EXISTED(
        409,
        "U001",
        "이미 회원가입을 완료한 이메일입니다."
    ),
    INVALID_EMAIL_FORMAT(
        400,
        "U002",
        "이메일 형식이 유효하지 않습니다."
    );

    private final int status;
    private final String code;
    private final String message;

}
