package org.example.notification.adapter.in.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.JsonConverter;
import org.example.WebAdapter;
import org.example.aop.NotificationClient;
import org.example.event.notification.SendNotification;
import org.example.notification.application.factory.NotificationFactory;
import org.example.notification.application.port.in.command.NotificationType;
import org.example.notification.application.port.in.usecase.Notification;
import org.example.notification.application.port.out.RegisterNotificationPort;
import org.springframework.kafka.annotation.KafkaListener;

@WebAdapter
@RequiredArgsConstructor
@Slf4j
public class NotificationCreateConsumer {
    private final NotificationFactory notificationFactory;
    private JsonConverter jsonConverter;
    @KafkaListener(topics = "${kafka.notification.topic}", groupId = "${kafka.notification.group}")
    public void sendMessageListener(String requestJson) {

        SendNotification request = null;
        NotificationClient rq = null;
        try{
            rq = jsonConverter.convert(requestJson, NotificationClient.class);

            Notification notification = notificationFactory.createNotification(NotificationType.EVENT_COUPON);
            notification.sendMessage(rq);

        }catch (Exception e){
            log.error("error request {}", request);
            e.printStackTrace();
        }
    }
}
