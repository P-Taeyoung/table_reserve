package com.zerobase.table_reserve.exception;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ApiExceptionAdvice {
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<CustomException.CustomExceptionResponse>
        handleCustomException(HttpServletRequest req, final CustomException e) {

        // 예외 로그 기록
        log.error("CustomException 발생: URL = {}, ErrorCode = {}, Message = {}",
                req.getRequestURI(), e.getErrorCode().name(), e.getMessage());

        return ResponseEntity.status(e.getStatus()).body(
                CustomException.CustomExceptionResponse.builder()
                        .status(e.getStatus())
                        .code(e.getErrorCode().name())
                        .message(e.getMessage())
                        .build());

    }

    // CustomException 외의 모든 예외 처리
    @ExceptionHandler(Exception.class)
    public ResponseEntity<CustomException.CustomExceptionResponse>
        handleGeneralException(HttpServletRequest req ,final Exception e) {

        // 예외 로그 기록
        log.error("알 수 없는 예외 발생: URL = {}, Message = {}", req.getRequestURI(), e.getMessage(), e);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                CustomException.CustomExceptionResponse.builder()
                        .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .code("INTERNAL_SERVER_ERROR")
                        .message("서버에서 알 수 없는 오류가 발생했습니다.")
                        .build());
    }
}
