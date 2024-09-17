package org.example.coupon.infra.error;

import org.springframework.http.HttpStatus;

public enum CouponErrorCode implements ErrorCode {
    COUPON_NOT_FOUND(HttpStatus.NOT_FOUND,"해당 쿠폰을 찾을 수 없습니다."),

    COUPON_NO_VALIDATE(HttpStatus.BAD_REQUEST,"권한 및 주문이 잘못되었습니다"),

    COUPON_EXIST(HttpStatus.BAD_REQUEST,"이미 쿠폰이 존재합니다 발급은 하나만 가능합니다.")
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
