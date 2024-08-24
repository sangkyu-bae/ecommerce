package org.example.notification.application.port.in.usecase;

import org.example.event.notification.RegisterSSECommand;
import org.example.notification.application.port.in.command.RegisterSSENotificationCommand;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

public interface RegisterSSENotificationUseCase {
    SseEmitter subscribe(RegisterSSENotificationCommand command);

    void subscribe(RegisterSSECommand command);
}
