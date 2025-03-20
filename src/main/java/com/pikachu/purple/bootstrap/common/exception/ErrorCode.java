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
    UNAUTHORIZED_REQUEST(
        403,
        "C002",
        "인가되지 않은 요청입니다."
    ),

    // User
    EMAIL_ALREADY_EXISTS(
        409,
        "U001",
        "이미 회원가입을 완료한 이메일입니다."
    ),
    INVALID_EMAIL_FORMAT(
        400,
        "U002",
        "이메일 형식이 유효하지 않습니다."
    ),

    USER_NOT_FOUND(
        404,
        "U003",
        "사용자를 찾을 수 없습니다."
    ),

    NICKNAME_ALREADY_EXISTS(
        409,
        "U004",
        "이미 사용중인 닉네임입니다."
    ),

    INVALID_VERIFY_CODE(
        400,
        "U005",
        "인증코드가 유효하지 않습니다."
    ),

    USER_NOT_AUTHENTICATED(
        404,
        "U006",
        "인증되지 않은 사용자입니다."
    ),

    ALREADY_LOGOUT_EXCEPTION(
        404,
        "U007",
        "이미 로그아웃 되었습니다."
    ),

    // Jwt
    ACCESS_TOKEN_EXPIRED_EXCEPTION(
        400,
        "J001",
        "액세스 토큰이 만료되었습니다."
    ),
    REFRESH_TOKEN_EXPIRED_EXCEPTION(
        400,
        "J002",
        "리프레시 토큰이 만료되었습니다."
    ),
    JWT_VERIFICATION_EXCEPTION(
        400,
        "J003",
        "토큰 유효성 검사에 실패했습니다."
    ),
    JWT_EXPIRED_EXCEPTION(
        400,
        "J004",
        "토큰이 만료되었습니다."
    ),
    REFRESH_TOKEN_NOT_FOUND(
        404,
        "J005",
        "리프레시 토큰을 찾을 수 없습니다."
    ),
    NOT_VALID_LOGIN_ID_TOKEN(
        400,
        "J006",
        "유효하지 않은 ID Token입니다. 토큰을 새롭게 발급받아 사용하세요."
    ),

    // S3
    FILE_EMPTY_EXCEPTION(
        400,
        "S001",
        "업로드 파일이 비어있습니다."
    ),
    FILE_UPLOAD_FAIL_EXCEPTION(
        400,
        "S002",
        "파일을 업로드하는데 실패하였습니다."
    ),
    INVALID_FILE_EXTENSION_EXCEPTION(
        400,
        "S003",
        "파일 확장자명이 올바르지 않습니다."
    ),
    FILE_DELETE_FAIL_EXCEPTION(
        400,
        "S004",
        "파일을 삭제하는데 실패했습니다."
    ),

    // Perfume
    PERFUME_NOT_FOUND(
        404,
        "PF001",
        "해당하는 향수 정보가 존재하지 않습니다."
    ),

    // PerfumeAccord
    PERFUME_ACCORD_NOT_FOUND(
        404,
        "PFA001",
        "해당하는 향수의 어코드가 존재하지 않습니다."
    ),

    // Review
    REVIEW_NOT_FOUND(
        404,
        "RV001",
        "해당하는 리뷰가 존재하지 않습니다."
    ),
    SORT_TYPE_NOT_FOUND(
        404,
        "RV002",
        "해당하는 정렬 타입이 존재하지 않습니다."
    ),
    REVIEW_ALREADY_EXISTS(
        409,
        "RV003",
        "이미 리뷰가 존재합니다."
    ),

    // Rating
    STAR_RATING_NOT_FOUND(
        404,
        "RT001",
        "해당하는 별점이 존재하지 않습니다."
    ),

    // Accord
    ACCORD_NOT_FOUND(
        404,
        "AC001",
        "해당하는 어코드가 존재하지 않습니다."
    ),

    // UserAccord
    USER_ACCORD_NOT_FOUND(
        404,
        "UAC001",
        "해당하는 사용자 선호 어코드가 존재하지 않습니다."
    ),

    // Complaint
    COMPLAINT_NOT_FOUND(
        404,
    "CP001",
    "해당하는 신고 내역이 존재하지 않습니다."
    ),

    //Like
    LIKE_NOT_FOUND(
        404,
        "L001",
        "해당하는 좋아요가 존재하지 않습니다."
    ),
    LIKE_ALREADY_EXISTS(
        409,
        "L002",
        "이미 좋아요가 존재합니다."
    ),

    // Mood
    MOOD_NOT_FOUND(
        404,
        "M001",
        "해당하는 분위기가 존재하지 않습니다."
    );

    private final int status;
    private final String code;
    private final String message;

}
