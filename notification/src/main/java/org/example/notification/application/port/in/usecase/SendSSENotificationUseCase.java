package org.example.notification.application.port.in.usecase;

import org.example.aop.NotificationClient;

public interface SendSSENotificationUseCase {
    void sendMessage(NotificationClient client);
}
