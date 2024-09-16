package org.example.event.notification;

import java.lang.reflect.Array;
import java.util.Arrays;

public enum SSENotificationType {
    EVENT_COUPON(1,"event-coupon-"),
    QUEUE_EVENT(0,"대기열 이벤트");

    private final int type;

    private final String name;

    SSENotificationType(int type, String name){
        this.type = type;
        this.name = name;
    }

    public int getType(){
        return this.type;
    }

    public String getName(){
        return this.name;
    }

    public static SSENotificationType findSSENotificationType(int type){
        return Arrays.stream(SSENotificationType.values())
                .filter(sseNotificationType -> sseNotificationType.getType() == type)
                .findFirst()
                .orElseThrow();
    }
}
