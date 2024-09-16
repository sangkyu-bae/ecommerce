package org.example.notification.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.EnumMapperValue;
import org.example.UseCase;
import org.example.aop.NotificationClient;
import org.example.event.notification.RegisterSSECommand;
import org.example.event.notification.SendNotification;
import org.example.notification.adapter.out.persistence.NotificationResponse;
import org.example.notification.application.port.in.command.RegisterSSENotificationCommand;
import org.example.notification.application.port.in.usecase.Notification;
import org.example.notification.application.port.out.RegisterNotificationPort;
import org.example.notification.application.port.out.SendNotificationPort;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@UseCase("event")
@Slf4j
@RequiredArgsConstructor
public class EventCouponNotification implements Notification {

    private final RegisterNotificationPort registerNotificationPort;
    private final SendNotificationPort sendNotificationPort;
    @Override
    public SseEmitter subscribe(RegisterSSENotificationCommand command) {
        return registerNotificationPort.subscribe(command.getUserId(),"event-coupon-" + command.getEventName());
    }

    @Override
    public void sendMessage(NotificationClient send) {
        SendNotification sendNotification = SendNotification.builder()
                .fromMember(send.getFormMember())
                .eventName(send.getEventName())
                .notification(send.getNotification())
                .eventType(new EnumMapperValue(send.getType()))
                .statusType(new EnumMapperValue(send.getSseStatusType()))
                .build();
        sendNotificationPort.sendMessage(sendNotification);
    }


}
