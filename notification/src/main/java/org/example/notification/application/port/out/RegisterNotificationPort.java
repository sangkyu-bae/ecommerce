package org.example.notification.application.port.out;

import org.example.event.notification.RegisterSSECommand;
import org.example.event.notification.SendNotification;
import org.example.notification.adapter.out.persistence.OrderNotificationType;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

public interface RegisterNotificationPort {
    SseEmitter subscribe(Long memberId,String eventName);

    SseEmitter subscribe(RegisterSSECommand command);

    void sendAllMessage();
}
