package com.example.adminservice.infra.error;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {
        return new ResponseEntity<>("An error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(value = { CustomException.class })
    protected ResponseEntity<ErrorResponse> handleCustomException(CustomException e) {
        log.error("handleCustomException throw CustomException : {}", e.getErrorCode());
        log.error("error method : {}", e.getMethod()+"()");
        return ErrorResponse.toResponseEntity(e.getErrorCode(),e.getMethod()+"()");
    }

    @ExceptionHandler(value = { ErrorException.class })
    protected ResponseEntity<ErrorResponse> handleErrorException(ErrorException e) {
        log.error("handleCustomException throw CustomException : {}", e.getErrorCode());
        log.error("error method : {}", e.getMethod()+"()");
        e.printStackTrace();
        return ErrorResponse.toResponseEntity(e.getErrorCode(),e.getMethod()+"()");
    }
}
