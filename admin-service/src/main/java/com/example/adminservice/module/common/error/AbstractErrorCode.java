package com.example.adminservice.module.common.error;

import org.springframework.http.HttpStatus;

public abstract class AbstractErrorCode implements ErrorCode {
    private final HttpStatus httpStatus;
    private final String detail;

    protected AbstractErrorCode(HttpStatus httpStatus, String detail) {
        this.httpStatus = httpStatus;
        this.detail = detail;
    }

    @Override
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    @Override
    public String getDetail() {
        return detail;
    }
}
