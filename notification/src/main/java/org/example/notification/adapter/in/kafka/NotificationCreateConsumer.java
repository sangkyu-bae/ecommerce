package org.example.notification.adapter.in.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.WebAdapter;
import org.example.aop.NotificationClient;
import org.example.event.notification.SendNotification;
import org.example.notification.application.port.out.RegisterNotificationPort;
import org.springframework.kafka.annotation.KafkaListener;

@WebAdapter
@RequiredArgsConstructor
@Slf4j
public class NotificationCreateConsumer {

    private final ObjectMapper objectMapper;

    private final RegisterNotificationPort port;

    @KafkaListener(topics = "${kafka.notification.topic}", groupId = "${kafka.notification.group}")
    public void orderStatusListener(String requestJson) {
//        RequestOrderStatus request = null;
//        try {
//            request = objectMapper.readValue(orderStatusJson, RequestOrderStatus.class);
//            OrderNotificationType type = OrderNotificationType.findStatusCode(request.getOrderStatus());
//            port.sendMessage(request.getMemberId(),type);
//        } catch (Exception e) {
//            log.error("error request {}", request);
//            throw new RuntimeException(e);
//        }

        SendNotification request = null;
        NotificationClient rq = null;
        try{
            rq = objectMapper.readValue(requestJson, NotificationClient.class);
//            request = objectMapper.readValue(requestJson, SendNotification.class);
            log.info(rq.toString());
        }catch (Exception e){
            log.error("error request {}", request);
            e.printStackTrace();
        }
    }
}
