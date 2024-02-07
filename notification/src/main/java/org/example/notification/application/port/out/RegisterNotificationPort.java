package org.example.notification.application.port.out;

import org.example.notification.adapter.out.persistence.OrderNotificationType;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

public interface RegisterNotificationPort {
    SseEmitter subscribe(Long memberId);

    void sendMessage(long memberEventId, OrderNotificationType type);
}
