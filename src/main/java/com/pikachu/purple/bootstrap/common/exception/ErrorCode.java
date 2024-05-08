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

    // Auth
    EMAIL_ALREADY_EXISTED(
        409,
        "A001",
        "이미 회원가입을 완료한 이메일입니다."
    ),
    INVALID_EMAIL_FORMAT(
        400,
        "A002",
        "이메일 형식이 유효하지 않습니다."
    ),

    USER_NOT_FOUND(
        404,
        "U003",
        "사용자를 찾을 수 없습니다."
    ),
  
    NICKNAME_ALREADY_EXISTED(
        409,
        "U004",
        "이미 사용중인 닉네임입니다."
    ),

    INVALID_VERIFY_CODE(
        400,
        "A003",
        "인증코드가 유효하지 않습니다."
    );

    private final int status;
    private final String code;
    private final String message;

}
