package org.example.coupon.infra.error;

import org.springframework.http.HttpStatus;

public enum CouponErrorCode implements ErrorCode {
    COUPON_NOT_FOUND(HttpStatus.NOT_FOUND,"해당 주문을 찾을 수 없습니다."),

    COUPON_NO_VALIDATE(HttpStatus.BAD_REQUEST,"권한 및 주문이 잘못되었습니다")
    ;

    private final HttpStatus httpStatus;
    private final String detail;
    CouponErrorCode(HttpStatus httpStatus, String detail){
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
