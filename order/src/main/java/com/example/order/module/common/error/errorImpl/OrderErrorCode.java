package com.example.order.module.common.error.errorImpl;

import com.example.order.module.common.error.ErrorCode;
import org.springframework.http.HttpStatus;

public enum OrderErrorCode implements ErrorCode {
    ORDER_NOT_FOUND(HttpStatus.NOT_FOUND,"해당 주문을 찾을 수 없습니다.");

    private final HttpStatus httpStatus;
    private final String detail;
    OrderErrorCode(HttpStatus httpStatus,String detail){
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
