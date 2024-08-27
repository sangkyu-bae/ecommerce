package org.example.notification.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.EnumMapperValue;
import org.example.UseCase;
import org.example.aop.NotificationClient;
import org.example.event.notification.SendNotification;
import org.example.notification.application.port.in.usecase.SendSSENotificationUseCase;
import org.example.notification.application.port.out.SendNotificationPort;

@UseCase
@Slf4j
@RequiredArgsConstructor
public class SendSSENotificationService implements SendSSENotificationUseCase {

    private final SendNotificationPort sendNotificationPort;
    @Override
    public void sendMessage(NotificationClient client) {
        SendNotification sendNotification = SendNotification.builder()
                .fromMember(client.getFormMember())
                .eventName(client.getEventName())
                .notification(client.getNotification())
                .eventType(new EnumMapperValue(client.getType()))
                .statusType(new EnumMapperValue(client.getSseStatusType()))
                .build();
        sendNotificationPort.sendMessage(sendNotification);
    }
}
