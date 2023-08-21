package com.example.adminservice.module.common.error;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

@Getter
@Builder
public class ErrorResponse {
    private final LocalDateTime timestamp =LocalDateTime.now();
    private final int status;
    private final String error;
    private final String code;
    private final String message;
    private final String method;
    public static ResponseEntity<ErrorResponse> toResponseEntity(ErrorCodet errorCodet, String method) {
        return ResponseEntity
                .status(errorCodet.getHttpStatus())
                .body(ErrorResponse.builder()
                        .status(errorCodet.getHttpStatus().value())
                        .error(errorCodet.getHttpStatus().name())
                        .code(errorCodet.name())
                        .message(errorCodet.getDetail())
                        .method(method)
                        .build()
                );
    }

    public static ResponseEntity<ErrorResponse> toResponseEntity(AbstractErrorCode error, String method) {
        return ResponseEntity
                .status(error.getHttpStatus())
                .body(ErrorResponse.builder()
                        .status(error.getHttpStatus().value())
                        .error(error.getHttpStatus().name())
                        .message(error.getDetail())
                        .method(method)
                        .build()
                );
    }
}
