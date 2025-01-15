package com.example.order.adapter.out.persistence.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EventStatus {

    INIT("이벤트 생성 대기"),
    FAIL("이벤트 생성 실패"),

    FAIL_WORK("이벤트 이전 동작 실패"),
    SUCCESS("이벤트 생성 완료")
    ;

    private final String status;
}
