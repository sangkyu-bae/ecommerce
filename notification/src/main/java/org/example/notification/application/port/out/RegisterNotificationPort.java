package org.example.notification.application.port.out;

import org.example.event.notification.RegisterSSECommand;
import org.example.event.notification.RequestNotification;
import org.example.notification.adapter.out.persistence.OrderNotificationType;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

public interface RegisterNotificationPort {
    SseEmitter subscribe(Long memberId);

    SseEmitter subscribe(RegisterSSECommand command);

    void sendMessage(long memberEventId, OrderNotificationType type);

    void sendMessage(RequestNotification request);
}
