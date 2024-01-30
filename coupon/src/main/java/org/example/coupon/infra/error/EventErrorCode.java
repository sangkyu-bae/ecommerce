package org.example.coupon.infra.error;

import org.springframework.http.HttpStatus;

public enum EventErrorCode implements ErrorCode {

    EVENT_COUPON_NOT_FOUND(HttpStatus.NOT_FOUND,"해당 이벤트 쿠폰을 찾을 수 없습니다."),
    EVENT_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED,"해당 쿠폰이 소진 되었습니다.")
    ;

    private final HttpStatus httpStatus;
    private final String detail;
    EventErrorCode(HttpStatus httpStatus, String detail){
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
