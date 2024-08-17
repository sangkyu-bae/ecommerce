package org.example.event.notification;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.Arrays;
import java.util.function.Function;

public enum SSEStatusType {

    DELETE(0,"삭제"),
    KEEP(1,"유지");

    private final int type;

    private final String name;

    SSEStatusType(int type, String name){
        this.type = type;
        this.name = name;
    }

    public int getType(){
        return this.type;
    }

    public String getName(){
        return this.name;
    }

    public static SSEStatusType findSSEStatusType(int type){

        return Arrays.stream(SSEStatusType.values())
                .filter(sseStatusType -> sseStatusType.getType() == type)
                .findFirst()
                .orElseThrow();
    }
}
