package org.example.notification.application.port.in.usecase;

import org.example.aop.NotificationClient;
import org.example.event.notification.RegisterSSECommand;
import org.example.event.notification.SendNotification;
import org.example.notification.application.port.in.command.RegisterSSENotificationCommand;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;


public interface Notification {

    SseEmitter subscribe(RegisterSSENotificationCommand command);

    void sendMessage(NotificationClient send);


}
