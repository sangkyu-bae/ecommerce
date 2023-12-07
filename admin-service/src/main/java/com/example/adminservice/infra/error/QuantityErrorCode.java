package com.example.adminservice.infra.error;

import org.springframework.http.HttpStatus;

public enum QuantityErrorCode implements ErrorCode {
    QUANTITY_NOT_FOUND(HttpStatus.NOT_FOUND,"해당 상품의 수량을 찾을 수 없습니다."),
    QUANTITY_BAD_CHANGE(HttpStatus.BAD_REQUEST,"해당 상품의 수량을 수정 할 수 없습니다.")
    ;
    private final HttpStatus httpStatus;
    private final String detail;

    QuantityErrorCode(HttpStatus httpStatus, String detail) {
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
