package com.pikachu.purple.bootstrap.common.exception;

import com.pikachu.purple.bootstrap.common.dto.ErrorResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<?> handleBusinessException(BusinessException e) {
        ErrorCode errorCode = e.getErrorCode();
        if (errorCode.getStatus() == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
            log.error("handleBusinessException", e);
        } else {
            log.warn("handleBusinessException", e);
        }
        return makeErrorResponse(errorCode);
    }

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<?> handleException(Throwable e) {
        log.error("handleException", e);
        return makeErrorResponse(ErrorCode.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<?> makeErrorResponse(ErrorCode errorCode) {
        return ResponseEntity.status(errorCode.getStatus())
            .body(
                ErrorResponse.of(
                    errorCode.getCode(),
                    errorCode.getMessage()
                )
            );
    }

}
