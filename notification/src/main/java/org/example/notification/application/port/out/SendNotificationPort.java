package org.example.notification.application.port.out;

import org.example.event.notification.SendNotification;
import org.example.notification.adapter.out.persistence.OrderNotificationType;

public interface SendNotificationPort {

    void sendMessage(long memberEventId, OrderNotificationType type);

    void sendMessage(SendNotification request);
}
