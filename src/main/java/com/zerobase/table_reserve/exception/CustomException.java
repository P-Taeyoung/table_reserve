package com.zerobase.table_reserve.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.io.Serializable;

@Getter
public class CustomException extends RuntimeException {
    private final ErrorCode errorCode;
    private final int status;

    public CustomException(ErrorCode errorCode) {
        super(errorCode.getDetail());
        this.errorCode = errorCode;
        this.status = errorCode.getHttpStatus().value();
    }


    @AllArgsConstructor
    @Builder
    @NoArgsConstructor
    @Getter
    public static class CustomExceptionResponse{
        private int status;
        private String code;
        private String message;
    }
}
