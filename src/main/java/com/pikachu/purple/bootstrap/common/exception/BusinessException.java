package com.pikachu.purple.bootstrap.common.exception;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {

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
    public static final BusinessException AccessTokenExpiredException = new BusinessException(
        ErrorCode.ACCESS_TOKEN_EXPIRED_EXCEPTION
    );
    public static final BusinessException RefreshTokenExpiredException = new BusinessException(
        ErrorCode.ACCESS_TOKEN_EXPIRED_EXCEPTION
    );
    public static final BusinessException RefreshTokenNotFoundException = new BusinessException(
        ErrorCode.REFRESH_TOKEN_NOT_FOUND
    );
    public static final BusinessException NotValidLoginIdTokenException = new BusinessException(
        ErrorCode.NOT_VALID_LOGIN_ID_TOKEN
    );
    public static final BusinessException FileEmptyException = new BusinessException(
        ErrorCode.FILE_EMPTY_EXCEPTION
    );
    public static final BusinessException FileUploadFailException = new BusinessException(
        ErrorCode.FILE_UPLOAD_FAIL_EXCEPTION
    );
    public static final BusinessException InvalidFileExtensionException = new BusinessException(
        ErrorCode.INVALID_FILE_EXTENSION_EXCEPTION
    );
    public static final BusinessException FileDeleteFailException = new BusinessException(
        ErrorCode.FILE_DELETE_FAIL_EXCEPTION
    );
    public static final BusinessException ReviewNotFoundException = new BusinessException(
        ErrorCode.REVIEW_NOT_FOUND
    );
    public static final BusinessException RatingNotFoundException = new BusinessException(
        ErrorCode.RATING_NOT_FOUND
    );

    private final ErrorCode errorCode;

    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

}
