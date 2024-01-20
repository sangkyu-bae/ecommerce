package org.example.basket.infra.error;

import org.springframework.http.HttpStatus;

public enum BasketErrorCode implements ErrorCode {
    BASKET_NOT_FOUND(HttpStatus.NOT_FOUND,"해당 장바구니 정보를 찾을 수 없습니다."),

    BASKET_NO_VALIDATE(HttpStatus.BAD_REQUEST,"권한 및 장바구니 정보가 잘못되었습니다"),
    PRODUCT_NOT_FOUND(HttpStatus.NOT_FOUND,"해당 상품을 찾을 수 없습니다."),;

    private final HttpStatus httpStatus;
    private final String detail;
    BasketErrorCode(HttpStatus httpStatus, String detail){
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
