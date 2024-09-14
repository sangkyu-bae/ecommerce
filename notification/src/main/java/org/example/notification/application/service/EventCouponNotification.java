package org.example.notification.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.UseCase;
import org.example.event.notification.RegisterSSECommand;
import org.example.event.notification.SendNotification;
import org.example.notification.adapter.out.persistence.NotificationResponse;
import org.example.notification.application.port.in.command.RegisterSSENotificationCommand;
import org.example.notification.application.port.in.usecase.Notification;
import org.example.notification.application.port.out.RegisterNotificationPort;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@UseCase("event")
@Slf4j
@RequiredArgsConstructor
public class EventCouponNotification implements Notification {

    private final RegisterNotificationPort registerNotificationPort;

    @Override
    public SseEmitter subscribe(RegisterSSENotificationCommand command) {
        return registerNotificationPort.subscribe(command.getUserId(),command.getEventName());
    }

    @Override
    public void sendMessage(SendNotification send) {

    }


}
