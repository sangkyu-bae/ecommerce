package org.example.notification.adapter.out.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.EnumMapperValue;
import org.example.PersistenceAdapter;
import org.example.aop.NotificationClient;
import org.example.event.notification.RegisterSSECommand;
import org.example.event.notification.SendNotification;
import org.example.event.notification.SSEStatusType;
import org.example.notification.adapter.out.persistence.NotificationResponse;
import org.example.notification.adapter.out.persistence.OrderNotificationType;
import org.example.notification.application.port.out.RegisterNotificationPort;
import org.example.notification.application.port.out.SendNotificationPort;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;

@PersistenceAdapter
@RequiredArgsConstructor
@Slf4j
public class NotificationAdapter implements RegisterNotificationPort,
                                            SendNotificationPort {

    private final EmitterRepository emitterRepository;
    private static final Long DEFAULT_TIMEOUT = 60L * 1000 * 60;
//    private static final Long DEFAULT_TIMEOUT =10L * 1000;

    @Override
    public SseEmitter subscribe(Long memberId,String eventName,String sendMsg) {
//        String emitterId = eventName+ ":" +String.valueOf(memberId) ;
        String emitterId = eventName ;
        SseEmitter emitter = emitterRepository.save(emitterId,memberId, new SseEmitter(DEFAULT_TIMEOUT));
        emitter.onCompletion(() -> {
            log.error("Emitter completed: {}", emitterId);
            emitterRepository.deleteById(emitterId);
        });
        emitter.onTimeout(() -> {
            log.error("Emitter timeout: {}", emitterId);
            emitterRepository.deleteById(emitterId);
        });

        NotificationResponse response = NotificationResponse.builder()
                .statusType(new EnumMapperValue(SSEStatusType.CONNECT))
                .eventType(new EnumMapperValue(NotificationClient.NotificationType.QUEUE_EVENT))
                .sendMessage(sendMsg)
                .build();
        // 503 에러를 방지하기 위한 더미 이벤트 전송
        sendNotification(emitter,
                emitterId,
                response
        );

        log.info("connect emitterId : {}", emitterId);

        return emitter;
    }

    @Override
    public SseEmitter subscribe(RegisterSSECommand command) {
        String emitterId = command.getNotificationType().getName();
        SseEmitter emitter = emitterRepository.save(emitterId,command.getUserId(), new SseEmitter(DEFAULT_TIMEOUT));
        emitter.onCompletion(() -> emitterRepository.deleteByKeyAndId(emitterId,command.getUserId()));
        emitter.onTimeout(() -> emitterRepository.deleteByKeyAndId(emitterId,command.getUserId()));

        NotificationResponse response = NotificationResponse.builder()
                .statusType(new EnumMapperValue(SSEStatusType.CONNECT))
                .eventType(new EnumMapperValue(NotificationClient.NotificationType.QUEUE_EVENT))
                .sendMessage("연결에 성공하였습니다")
                .build();

        sendNotification(
                emitter,
                emitterId,
                response
        );

        log.info("connect emitterId : {}", emitterId);
        return emitter;
    }

    @Override
    public void sendMessage(long memberEventId, OrderNotificationType type) {
        String eventId = String.valueOf(memberEventId);
        SseEmitter emitter = emitterRepository.findEmitterMemberId(eventId);

        sendNotification(
                emitter,
                eventId,
                type.getTypeStatus()
        );
        emitterRepository.deleteById(eventId);
    }
    @Override
    public void sendMessage(SendNotification send) {
//        String eventId = send.getEventType().getName() + ":" +String.valueOf(send.getFromMember()) ;
        String eventId = send.getEventName();
        log.info("eventID : {}", eventId);
        log.info("member ID : {}", send.getFromMember());
        SseEmitter emitter = emitterRepository.findEmitterMemberId(eventId,send.getFromMember());

        NotificationResponse responseMessage = NotificationResponse.builder()
                .statusType(send.getStatusType())
                .eventType(send.getEventType())
                .sendMessage(send.getNotification())
                .build();

        sendNotification(
                emitter,
                eventId,
               responseMessage
        );
    }

    @Override
    public void sendAllMessage() {
        emitterRepository.findAll();
    }


    private void sendNotification(SseEmitter emitter,String emitterId, Object data) {
        try {
            log.info("send : {}", data);

            emitter.send(
                    SseEmitter.event()
                    .id(emitterId)
                    .data(data)
            );


        } catch (IOException exception) {
            log.error("send error");
            emitterRepository.deleteById(emitterId);
        }
    }
}
