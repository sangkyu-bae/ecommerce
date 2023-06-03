package com.example.adminservice.module.common.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    /* 400 BAD_REQUEST : 잘못된 요청 */
    PRODUCT_FORM_NO_VALIAD(HttpStatus.BAD_REQUEST,"잘못된 product 형식입니다"),
    /* 404 NOT_FOUND : Resource 를 찾을 수 없음 */
    PRODUCT_NOT_FOUND(HttpStatus.NOT_FOUND,"해당 상품을 찾을 수 없습니다.");


    private final HttpStatus httpStatus;
    private final String detail;
}
