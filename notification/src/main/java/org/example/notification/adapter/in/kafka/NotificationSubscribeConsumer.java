package org.example.notification.adapter.in.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.JsonConverter;
import org.example.WebAdapter;
import org.example.notification.application.factory.NotificationFactory;
import org.example.notification.application.port.in.command.NotificationType;
import org.example.notification.application.port.in.command.RegisterSSENotificationCommand;
import org.example.notification.application.port.in.usecase.Notification;
import org.springframework.kafka.annotation.KafkaListener;

@WebAdapter
@RequiredArgsConstructor
@Slf4j
public class NotificationSubscribeConsumer {
    private final NotificationFactory notificationFactory;

    private JsonConverter jsonConverter;

    @KafkaListener(topics = "${}",groupId = "${}")
    public void sendMessageListener(String requestJson){
        RegisterSSENotificationCommand command = null;

        try{
            command = jsonConverter.convert(requestJson,RegisterSSENotificationCommand.class);
            Notification notification = notificationFactory.createNotification(NotificationType.EVENT_COUPON);
        }catch (Exception e){

        }
    }
}
