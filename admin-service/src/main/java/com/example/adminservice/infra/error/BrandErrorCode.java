package com.example.adminservice.infra.error;

import org.springframework.http.HttpStatus;

public enum BrandErrorCode implements ErrorCode {
    BRAND_UNPROCESSABLE_ENTITY(HttpStatus.UNPROCESSABLE_ENTITY,"잘못된 brand 형식입니다."),
    BRAND_FORM_NO_VALIDATE(HttpStatus.BAD_REQUEST,"잘못된 Brand 형식입니다."),
    BRAND_NOT_FOUND(HttpStatus.NOT_FOUND,"해당 브랜드를 찾을 수 없습니다.");

    private final HttpStatus httpStatus;
    private final String detail;

    BrandErrorCode(HttpStatus httpStatus, String detail) {
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
