package org.example.notification.adapter.out.persistence;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum OrderNotificationType {

    FAIL_NO_QUANTITY(1,"수량이 부족 합니다."),
    FAIL_NO_COUPON(2,"쿠폰을 사용할 수 없습니다"),
    SUCCESS(3,"주문이 성공 하였습니다.");

    private final int status;
    private final String typeStatus;

    OrderNotificationType(int status, String typeStatus){
        this.status = status;
        this.typeStatus = typeStatus;
    }

    public static OrderNotificationType findStatusCode(int status){
        return Arrays.stream(OrderNotificationType.values())
                .filter(statusCode -> statusCode.getStatus()== status)
                .findFirst()
                .orElseThrow();
    }

}
