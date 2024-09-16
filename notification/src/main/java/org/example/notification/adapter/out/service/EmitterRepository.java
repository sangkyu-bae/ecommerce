package org.example.notification.adapter.out.service;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.List;
import java.util.Map;

public interface EmitterRepository {

    SseEmitter save(String emitterId, SseEmitter sseEmitter);

    SseEmitter save(String emitterId, Long userId,SseEmitter emitter);

    void saveEventCache(String emitterId, Object event);
    Map<String, SseEmitter> findAllEmitterStartWithByMemberId(String userId);
    Map<String, Object> findAllEventCacheStartWithByMemberId(String userId);
    void deleteById(String id);
    void deleteByKeyAndId(String key,long userId);
    void deleteAllEmitterStartWithId(String userId);
    void deleteAllEventCacheStartWithId(String userId);

    SseEmitter findEmitterMemberId(String id);

    SseEmitter findEmitterMemberId(String eventId,Long id);

    List<SseEmitter> findAll();
}
