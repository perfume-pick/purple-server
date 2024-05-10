package com.pikachu.purple.bootstrap.common.exception;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {

    private final ErrorCode errorCode;

    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public static final BusinessException EmailExistedException = new BusinessException(
        ErrorCode.EMAIL_ALREADY_EXISTED
    );

    public static final BusinessException InvalidEmailException = new BusinessException(
        ErrorCode.INVALID_EMAIL_FORMAT
    );

    public static final BusinessException UserNotFoundException = new BusinessException(
        ErrorCode.USER_NOT_FOUND
    );

    public static final BusinessException NicknameAlreadyExistedException = new BusinessException(
        ErrorCode.NICKNAME_ALREADY_EXISTED
    );

    public static final BusinessException InvalidVerifyCodeException = new BusinessException(
        ErrorCode.INVALID_VERIFY_CODE
    );

}
