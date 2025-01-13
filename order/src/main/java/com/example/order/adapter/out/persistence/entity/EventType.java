package com.example.order.adapter.out.persistence.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EventType {
    DELIVERY("주문 이벤트");

    private final String status;
}
