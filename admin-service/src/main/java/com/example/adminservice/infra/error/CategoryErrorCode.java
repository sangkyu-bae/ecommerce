package com.example.adminservice.infra.error;

import org.springframework.http.HttpStatus;

public enum CategoryErrorCode implements ErrorCode {
    BRAND_NOT_FOUND(HttpStatus.NOT_FOUND,"해당 카테고리를 찾을 수 없습니다.");

    private final HttpStatus httpStatus;
    private final String detail;

    CategoryErrorCode(HttpStatus httpStatus, String detail) {
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
