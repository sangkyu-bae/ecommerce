package org.example.notification.application.port.in.usecase;

import org.example.notification.application.port.in.command.RegisterSSENotificationCommand;

public interface RegisterSSENotificationUseCase {
    void subscribe(RegisterSSENotificationCommand command);
}
