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
    public static final BusinessException PerfumeNotFoundException = new BusinessException(
        ErrorCode.PERFUME_NOT_FOUND
    );
    public static final BusinessException PerfumeAccordNotFoundException = new BusinessException(
        ErrorCode.PERFUME_ACCORD_NOT_FOUND
    );
    public static final BusinessException ReviewNotFoundException = new BusinessException(
        ErrorCode.REVIEW_NOT_FOUND
    );
    public static final BusinessException StarRatingNotFoundException = new BusinessException(
        ErrorCode.STAR_RATING_NOT_FOUND
    );
    public static final BusinessException SortTypeNotFoundException = new BusinessException(
        ErrorCode.SORT_TYPE_NOT_FOUND
    );
    public static final BusinessException AccordNotFountException = new BusinessException(
        ErrorCode.ACCORD_NOT_FOUND
    );
    public static final BusinessException UserAccordNotFoundException = new BusinessException(
        ErrorCode.USER_ACCORD_NOT_FOUND
    );
    public static final BusinessException ComplaintNotFoundException = new BusinessException(
        ErrorCode.COMPLAINT_NOT_FOUND
    );
    public static final BusinessException LikeNotFoundException = new BusinessException(
        ErrorCode.LIKE_NOT_FOUND
    );
    public static final BusinessException LikeAlreadyExistedException = new BusinessException(
        ErrorCode.LIKE_ALREADY_EXISTED
    );

    private final ErrorCode errorCode;

    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

}
