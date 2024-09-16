package org.example.notification.application.port.in.command;

public enum NotificationType {

    EVENT_COUPON("이벤트 쿠폰 알림","event"),

    NOTIFICATION("실시간 알림","normal");


    private final String name;

    private final String type;

    NotificationType(String name, String type){
        this.name = name;
        this.type = type;
    }

    public String getName(){
        return name;
    }

    public String getType(){
        return type;
    }


}
