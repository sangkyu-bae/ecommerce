package com.example.adminservice.infra.error;

import org.springframework.http.HttpStatus;

public enum ProductErrorCode implements ErrorCode {
    PRODUCT_FORM_NO_VALIDATE(HttpStatus.BAD_REQUEST,"잘못된 Product 형식입니다."),
    PRODUCT_NOT_FOUND(HttpStatus.NOT_FOUND,"해당 브랜드를 찾을 수 없습니다."),
    PRODUCT_NO_CONTENT(HttpStatus.NO_CONTENT,"상품 정보를 찾을 수 없습니다.")
    ;
    private final HttpStatus httpStatus;
    private final String detail;

    ProductErrorCode(HttpStatus httpStatus, String detail){
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
