package com.pikachu.purple.bootstrap.common.dto;

import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class SuccessResponse<T> {

    private final LocalDateTime timeStamp;
    private final T responseData;

    public SuccessResponse(T responseData) {
        this.timeStamp = LocalDateTime.now();
        this.responseData = responseData;
    }

    public static <T> SuccessResponse<T> of(T data) {
        return new SuccessResponse<>(data);
    }

}
