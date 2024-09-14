package org.example.notification.application.factory;

import org.example.notification.application.port.in.command.NotificationType;
import org.example.notification.application.port.in.usecase.Notification;

public interface NotificationFactory {
    Notification createNotification(NotificationType type);
}
