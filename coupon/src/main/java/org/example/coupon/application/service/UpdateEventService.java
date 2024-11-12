package org.example.coupon.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.UseCase;
import org.example.aop.Notification;
import org.example.aop.NotificationClient;
import org.example.coupon.adapter.out.persistence.entity.CouponEntity;
import org.example.coupon.adapter.out.persistence.entity.EventEntity;
import org.example.coupon.application.port.in.command.CouponIssuanceCommand;
import org.example.coupon.application.port.in.command.UpdateEventCouponCommand;
import org.example.coupon.application.port.in.usecase.UpdateEventCouponUseCase;
import org.example.coupon.application.port.out.*;
import org.example.coupon.domain.Coupon;
import org.example.coupon.domain.CouponComponent;
import org.example.coupon.domain.Event;
import org.example.coupon.infra.error.CouponErrorCode;
import org.example.coupon.infra.error.ErrorException;
import org.example.coupon.infra.redis.DistributedLock;
import org.example.event.notification.SSEStatusType;
import org.example.kafka.NotificationProducer;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;


@UseCase
@RequiredArgsConstructor
@Slf4j
//@Transactional
public class UpdateEventService implements UpdateEventCouponUseCase {

    private final UpdateEventPort updateEventPort;
    private final RegisterCouponPort registerCouponPort;

    private final UpdateEventCouponPort updateEventCoupon;
    private final UpdateEventCouponPort updateEventCouponPort;
    private final FindEventPort findEventPort;
    private final FindCouponPort findCouponPort;
    private final UpdateCouponPort updateCouponPort;

    private final NotificationProducer notificationProducer;


    @Override
    @DistributedLock(key = "#couponName")
    public boolean decreaseEventCoupon(String couponName, CouponIssuanceCommand command) {
//        updateEventPort.redeemEvent(new Event.EventId(command.getEventCouponId()));

        EventEntity eventEntity = updateEventPort.decreaseEventCoupon(new Event.EventId(command.getEventCouponId()));

        log.info("basic !! ");
        if(eventEntity.getQuantity() < 1){
            log.error("ERRROR : !!");
            return false;
        }
        CouponComponent couponComponent = CouponComponent.createGenerateCouponComponentVo(
                new CouponComponent.CouponComponentId(null),
                new CouponComponent.CouponComponentUserId(command.getUserId()),
                CouponComponent.CouponStatusCode.PUBLISH,
                new CouponComponent.CouponComponentEndAt(LocalDateTime.now()),
                null
        );
        registerCouponPort.issuanceCoupon(couponComponent,new Coupon.CouponId(command.getEventCouponId()));

        return true;
    }

    @Override
    public boolean isQueueEventCoupon(UpdateEventCouponCommand command) {
        return false;
    }

    @Override
    public void process() {

        LocalDateTime now = LocalDateTime.now();
        Event.EventStartAt eventStartAt = new Event.EventStartAt(now);

        List<EventEntity> eventList = findEventPort.findByStartAtAfter(eventStartAt);
        log.info("start >>>>>>>>>>>>>>");

        if(eventList.size() < 1){
            log.info("현재 이벤트가 없습니다.");
            return;
        }

        List<Queue<Long>> queueList = new ArrayList<>();

        for(EventEntity event : eventList){
            Queue<Long> queue = updateEventCouponPort.findEventQueue(event.getId());
            queueList.add(queue);
        }

        log.info(">>>>>>>>>>>>>>>>>>>>>> queue");
        /**
         * 300명씩 대기열에서 처리한다고 가정 추후 테스트후 변경
         * */
        for(int i = 0;i < queueList.size() ;i++){
            Queue<Long> queue = queueList.get(i);
            EventEntity event = eventList.get(i);
            CouponEntity couponEntity;
            try{
                couponEntity = findCouponPort.findByCouponName(new Coupon.CouponName(event.getCouponName()));
            }catch (ErrorException e){
                log.error(">>>>>>> 존재하지 않은쿠폰 이벤트 {} 쿠폰 process 종료",event.getCouponName());
                continue;
            }

            log.info("{} 이벤트 쿠폰 ", couponEntity.getName());

            queueProcess(queue,event,couponEntity);
        }
    }

    @Override
    @Transactional
    public void addEventQueue(UpdateEventCouponCommand command) {
        EventEntity eventEntity = findEventPort.findByEventId(new Event.EventId(command.getEventId()));
        CouponEntity coupon = findCouponPort.findByCouponName(new Coupon.CouponName(eventEntity.getCouponName()));
        boolean existCoupon = coupon.existCouponByUserId(command.getUserId());

        if(existCoupon){
            throw new ErrorException(CouponErrorCode.COUPON_EXIST,"addEventQueue");
        }

        updateEventCouponPort.addEventQueue(new Event.EventId(command.getEventId()),command.getUserId());
    }
    @Override
    public void queueProcess(Queue<Long> queue, EventEntity event,CouponEntity couponEntity){
        int count = 0;
        List<CouponComponent> couponComponents = new ArrayList<>();
        while (!queue.isEmpty()){
            long userId = queue.poll();
            count++;

            if(count > 300){
                /**
                 * sse 전송 모듈 필요 몇번째 남았는지
                 * */
                updateEventPort.sendNotification(userId,
                        "event-coupon-" + event.getId(),
                        "앞에"+ count + "명이 대기하고 있어요",
                        NotificationClient.NotificationType.EVENT_COUPON.getType(),
                        SSEStatusType.KEEP.getType());
                continue;
            }

            try{
                /**
                 * 이때 분산락 필요한지 확인필요
                 * */
                event.decreaseQuantity();
                updateEventCouponPort.removeQueue(event.getId(), userId);

                CouponComponent couponComponent = CouponComponent.createGenerateCouponComponentVo(
                        new CouponComponent.CouponComponentId(null),
                        new CouponComponent.CouponComponentUserId(userId),
                        CouponComponent.CouponStatusCode.PUBLISH,
                        new CouponComponent.CouponComponentEndAt(event.getEndAt()),
                        null
                );

                couponComponents.add(couponComponent);
                updateEventPort.sendNotification(userId,
                        "event-coupon-" + event.getId(),
                        event.getCouponName()+"쿠폰 발급 완료",
                        NotificationClient.NotificationType.EVENT_COUPON.getType(),
                        SSEStatusType.DELETE.getType());
            }catch (ErrorException e){
                log.error(">>>>>>> 이벤트 쿠폰 :{}, 수량 종료", event.getId());
                break;
            }
            if(count == 300){
                updateCouponPort.bulkInsertCouponComponent(couponComponents,couponEntity);
                couponComponents.clear();
            }
        }

        if(!couponComponents.isEmpty()){
            updateCouponPort.bulkInsertCouponComponent(couponComponents,couponEntity);
            couponComponents.clear();
        }
    }

    @Override
    @Transactional
    public boolean basicEventCoupon(long eventId, long userId) {
         EventEntity eventEntity = updateEventPort.decreaseEventCoupon(new Event.EventId(eventId));

        log.info("basic !! ");
        if(eventEntity.getQuantity() < 1){
            log.error("ERRROR : !!");
            return false;
        }

        CouponComponent couponComponent = CouponComponent.createGenerateCouponComponentVo(
                new CouponComponent.CouponComponentId(null),
                new CouponComponent.CouponComponentUserId(userId),
                CouponComponent.CouponStatusCode.PUBLISH,
                new CouponComponent.CouponComponentEndAt(LocalDateTime.now()),
                null
        );
        registerCouponPort.issuanceCoupon(couponComponent,new Coupon.CouponId(eventId));

        return true;
    }
}
