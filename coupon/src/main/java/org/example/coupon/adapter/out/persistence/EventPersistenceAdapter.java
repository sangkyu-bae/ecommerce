package org.example.coupon.adapter.out.persistence;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.PersistenceAdapter;
import org.example.aop.Notification;
import org.example.aop.NotificationClient;
import org.example.coupon.adapter.out.persistence.entity.EventEntity;
import org.example.coupon.adapter.out.persistence.repository.EventRepository;
import org.example.coupon.application.port.out.RegisterEventPort;
import org.example.coupon.application.port.out.UpdateEventPort;
import org.example.coupon.domain.Event;
import org.example.coupon.infra.error.ErrorException;
import org.example.coupon.infra.error.EventErrorCode;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@PersistenceAdapter
@RequiredArgsConstructor
@Slf4j
public class EventPersistenceAdapter implements RegisterEventPort, UpdateEventPort {

    @PersistenceContext
    private EntityManager entityManager;
    private Map<Long,EventEntity> eventCache = new ConcurrentHashMap<>();
    private final EventRepository eventRepository;
    @Override
    public EventEntity registerEvent(Event event) {

        EventEntity registerEventEntity = EventEntity.builder()
                .salePercent(event.getSalePercent())
                .couponName(event.getCouponName())
                .quantity(event.getQuantity())
                .startAt(event.getStartAt())
                .endAt(event.getEndAt())
                .build();

        return eventRepository.save(registerEventEntity);
    }

    @Override
    @Transactional(readOnly = false)
    public EventEntity decreaseEventCoupon(Event.EventId eventId) {
        EventEntity eventEntity = eventRepository.findById(eventId.getId())
                .orElseThrow(()->new ErrorException(EventErrorCode.EVENT_COUPON_NOT_FOUND,"decreaseEventCoupon"));

        eventEntity.decreaseQuantity();
        return eventRepository.save(eventEntity);
    }

    @Override
    @Notification(memberId = "#memberId", eventName = "#eventName", notification = "#notification", type = "#type", sseType = "#sseType")
    public void sendNotification(long memberId, String eventName, String notification, int type,int sseType) {
        log.info("{} 이벤트 쿠폰에 {}님은 {}",eventName , memberId, notification);
    }

    @Override
    @Transactional
    public boolean redeemEvent(Event.EventId eventId) {
        EventEntity cachedEvent = eventCache.get(eventId.getId());

        if(cachedEvent != null){
            cachedEvent.decreaseQuantity();
            entityManager.merge(cachedEvent);
            eventCache.put(eventId.getId(),cachedEvent);
            return true;
        }

        EventEntity eventEntity = entityManager.find(EventEntity.class,eventId.getId());

        if(eventEntity != null){
            eventEntity.decreaseQuantity();
            eventCache.put(eventId.getId(),eventEntity);
            return true;
        }

        return false;
    }


}
