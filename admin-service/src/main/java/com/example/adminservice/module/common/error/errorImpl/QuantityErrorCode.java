package com.example.adminservice.module.common.error.errorImpl;

import com.example.adminservice.module.common.error.ErrorCode;
import org.springframework.http.HttpStatus;

public enum QuantityErrorCode implements ErrorCode {
    QUANTITY_NOT_FOUND(HttpStatus.NOT_FOUND,"해당 상품의 수량을 찾을 수 없습니다.");
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
