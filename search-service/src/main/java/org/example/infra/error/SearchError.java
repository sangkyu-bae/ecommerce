package org.example.infra.error;

import org.springframework.http.HttpStatus;

public enum SearchError implements ErrorCode {
    NO_EXIST_PRODUCT(HttpStatus.NO_CONTENT,"해당 상품을 찾을 수 없습니다.");

    private final HttpStatus httpStatus;
    private final String detail;

    SearchError(HttpStatus httpStatus, String detail) {
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
