package com.pikachu.purple.bootstrap.common.dto;

import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class ErrorResponse {

    private final String responseCode;
    private final String responseStatus;
    private final LocalDateTime timeStamp;

    public ErrorResponse(String responseCode, String responseStatus) {
        this.responseCode = responseCode;
        this.responseStatus = responseStatus;
        this.timeStamp = LocalDateTime.now();
    }

    public static ErrorResponse of(String errorCode, String errorStatus) {
        return new ErrorResponse(errorCode, errorStatus);
    }

}
