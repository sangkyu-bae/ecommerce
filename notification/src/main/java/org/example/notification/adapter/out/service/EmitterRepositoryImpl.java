package org.example.notification.adapter.out.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import org.yaml.snakeyaml.emitter.Emitter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class EmitterRepositoryImpl implements EmitterRepository{
    private final Map<String, SseEmitter> emitters = new ConcurrentHashMap<>();
    private final Map<String, Object> eventCache = new ConcurrentHashMap<>();

    private final Map<String, Map<Long, SseEmitter>> sseEmitters = new ConcurrentHashMap<>();

    @Override
    public SseEmitter save(String emitterId, SseEmitter sseEmitter) {
        emitters.put(emitterId, sseEmitter);
        return sseEmitter;
    }

    @Override
    public SseEmitter save(String emitterId, Long userId, SseEmitter emitter) {

        Map<Long, SseEmitter> emitterMap = null;
        log.info("emitterId : {}",emitterId);
        log.info("userId : {}", userId);
        if(sseEmitters.containsKey(emitterId)){
            emitterMap = sseEmitters.get(emitterId);
            emitterMap.put(userId,emitter);
        }else{
            emitterMap = new HashMap<>();
            sseEmitters.put(emitterId, emitterMap);
        }

        return emitter;
    }

    @Override
    public void saveEventCache(String eventCacheId, Object event) {
        eventCache.put(eventCacheId, event);
    }

    @Override
    public Map<String, SseEmitter> findAllEmitterStartWithByMemberId(String memberId) {
        return emitters.entrySet().stream()
                .filter(entry -> entry.getKey().startsWith(memberId))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    @Override
    public SseEmitter findEmitterMemberId(String id) {
        SseEmitter emitter = sseEmitters.get(id).get(id);
        return emitter;
    }

    @Override
    public SseEmitter findEmitterMemberId(String eventId, Long id) {
        log.info("event Id : {}", eventId);
        log.info("meberId : {}", id);
        for(String key : sseEmitters.keySet()){
            log.info( "key : {}", key);
        }
        return sseEmitters.get(eventId).get(id);
    }


    @Override
    public Map<String, Object> findAllEventCacheStartWithByMemberId(String memberId) {
        return eventCache.entrySet().stream()
                .filter(entry -> entry.getKey().startsWith(memberId))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    @Override
    public void deleteById(String id) {
        emitters.remove(id);
    }

    @Override
    public void deleteByKeyAndId(String key, long userId) {
        sseEmitters.get(key).remove(userId);
    }

    @Override
    public void deleteAllEmitterStartWithId(String memberId) {
        emitters.forEach(
                (key, emitter) -> {
                    if (key.startsWith(memberId)) {
                        emitters.remove(key);
                    }
                }
        );
    }

    @Override
    public void deleteAllEventCacheStartWithId(String memberId) {
        eventCache.forEach(
                (key, emitter) -> {
                    if (key.startsWith(memberId)) {
                        eventCache.remove(key);
                    }
                }
        );
    }



    @Override
    public List<SseEmitter> findAll() {
        emitters.forEach((key, emitter) -> {
            try {
                log.info("Message to client");
                emitter.send("Message to client");
                emitter.send(SseEmitter.event()
                        .name(key)
                        .data("mssageSend"));
            } catch (Exception e) {
                // 예외 처리
                e.printStackTrace();
            }
        });

        return null;
    }
}
