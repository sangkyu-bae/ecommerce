package org.example.notification.application.factory;

import org.example.notification.application.port.in.command.NotificationType;
import org.example.notification.application.port.in.usecase.Notification;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ConcertNotificationFactory implements NotificationFactory{
    private final Map<String, Notification> notificationMap;

    public ConcertNotificationFactory(Map<String, Notification> notificationMap){
        this.notificationMap = notificationMap;
    }
    @Override
    public Notification createNotification(NotificationType type) {

        Notification notification = notificationMap.get(type.getType());

        if(notification == null){
            throw new IllegalArgumentException("Unknown notification type: " + type);//에러 공통화 필요
        }
        return notification;
    }
}
