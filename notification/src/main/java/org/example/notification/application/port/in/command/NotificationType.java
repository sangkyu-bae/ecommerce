package org.example.notification.application.port.in.command;

public enum NotificationType {

    EVENT_COUPON("이벤트 쿠폰 알림",1),

    NOTIFICATION("실시간 알림",2);


    private final String name;

    private final int type;

    NotificationType(String name, int type){
        this.name = name;
        this.type = type;
    }

    public String getName(){
        return name;
    }

    public int type(){
        return type;
    }


}
