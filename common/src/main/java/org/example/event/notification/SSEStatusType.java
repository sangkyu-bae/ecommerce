package org.example.event.notification;

import org.example.EnumMapper;
import java.util.Arrays;

public enum SSEStatusType implements EnumMapper {

    CONNECT(0,"연결성공"),
    DELETE(1,"삭제"),
    KEEP(2,"유지");


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
