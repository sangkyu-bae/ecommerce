package com.example.adminservice.infra.error;

import org.springframework.http.HttpStatus;

public enum ColorProductErrorCode implements ErrorCode {
    COLOR_PRODUCT_NOT_FOUND(HttpStatus.NOT_FOUND,"해당 상품의 컬러상품을 찾을 수 없습니다.");

    private final HttpStatus httpStatus;
    private final String detail;
    ColorProductErrorCode(HttpStatus httpStatus,String detail){
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
