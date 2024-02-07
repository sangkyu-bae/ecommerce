package org.example.notification.adapter.out.service;

import lombok.RequiredArgsConstructor;
import org.example.PersistenceAdapter;
import org.example.notification.adapter.out.persistence.OrderNotificationType;
import org.example.notification.application.port.out.RegisterNotificationPort;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Map;

@PersistenceAdapter
@RequiredArgsConstructor
public class NotificationAdapter implements RegisterNotificationPort {

    private final EmitterRepository emitterRepository;
    private static final Long DEFAULT_TIMEOUT = 60L * 1000 * 60;

    @Override
    public SseEmitter subscribe(Long memberId) {
        String emitterId = String.valueOf(memberId);
        SseEmitter emitter = emitterRepository.save(emitterId, new SseEmitter(DEFAULT_TIMEOUT));
        emitter.onCompletion(() -> emitterRepository.deleteById(emitterId));
        emitter.onTimeout(() -> emitterRepository.deleteById(emitterId));

        // 503 에러를 방지하기 위한 더미 이벤트 전송
        sendNotification(emitter,
                emitterId,
                "EventStream Created. [userId=" + memberId + "]"
        );

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


    private void sendNotification(SseEmitter emitter,String emitterId, Object data) {
        try {
            emitter.send(SseEmitter.event()
                    .id(emitterId)
                    .data(data));
        } catch (IOException exception) {
            emitterRepository.deleteById(emitterId);
        }
    }
}
