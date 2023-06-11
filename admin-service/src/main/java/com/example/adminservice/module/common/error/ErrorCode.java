package com.example.adminservice.module.common.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    /* 422  UNPROCESSABLE_ENTITY : 데이터 누락*/
    BRAND_UNPROCESSABLE_ENTITY(HttpStatus.UNPROCESSABLE_ENTITY,"잘못된 brand 형식입니다."),
    CATEGORY_UNPROCESSABLE_ENTITY(HttpStatus.UNPROCESSABLE_ENTITY,"잘못된 category 형식 입니다"),
    /* 400 BAD_REQUEST : 잘못된 요청 */
    PRODUCT_FORM_NO_VALIDATE(HttpStatus.BAD_REQUEST,"잘못된 product 형식입니다"),
    BRAND_FORM_NO_VALIDATE(HttpStatus.BAD_REQUEST,"잘못된 Brand 형식입니다."),
    COLOR_FORM_NO_VALIDATE(HttpStatus.BAD_REQUEST,"잘못된 Color 형식입니다."),

    SIZE_FORM_NO_VALIDATE(HttpStatus.BAD_REQUEST,"잘못된 size 형식입니다."),
    CATEGORY_FORM_NO_VALIDATE(HttpStatus.BAD_REQUEST,"잘못된 category 형식입니다"),
    /* 404 NOT_FOUND : Resource 를 찾을 수 없음 */
    PRODUCT_NOT_FOUND(HttpStatus.NOT_FOUND,"해당 상품을 찾을 수 없습니다."),
    BRAND_NOT_FOUND(HttpStatus.NOT_FOUND,"해당 브랜드를 찾을 수 없습니다.");


    private final HttpStatus httpStatus;
    private final String detail;
}
