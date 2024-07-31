package org.example.coupon.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.UseCase;
import org.example.coupon.adapter.out.persistence.entity.EventEntity;
import org.example.coupon.application.port.in.command.CouponIssuanceCommand;
import org.example.coupon.application.port.in.command.UpdateEventCouponCommand;
import org.example.coupon.application.port.in.usecase.UpdateEventCouponUseCase;
import org.example.coupon.application.port.out.FindEventPort;
import org.example.coupon.application.port.out.RegisterCouponPort;
import org.example.coupon.application.port.out.UpdateEventCouponPort;
import org.example.coupon.application.port.out.UpdateEventPort;
import org.example.coupon.domain.Coupon;
import org.example.coupon.domain.CouponComponent;
import org.example.coupon.domain.Event;
import org.example.coupon.infra.redis.DistributedLock;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;


@UseCase
@RequiredArgsConstructor
@Slf4j
@Transactional
public class UpdateEventService implements UpdateEventCouponUseCase {

    private final UpdateEventPort updateEventPort;

    private final RegisterCouponPort registerCouponPort;

    private final UpdateEventCouponPort updateEventCoupon;

    private final UpdateEventCouponPort updateEventCouponPort;

    private final FindEventPort findEventPort;

    @Override
    @DistributedLock(key = "#couponName")
    public boolean decreaseEventCoupon(String couponName, CouponIssuanceCommand command) {

        updateEventPort.decreaseEventCoupon(new Event.EventId(command.getEventCouponId()));

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
        Event.EventEndAt eventEndAt = new Event.EventEndAt(now);
        List<EventEntity> eventList = findEventPort.findByStartAtAfterAndEndAtBefore(eventStartAt,eventEndAt);

        if(eventList.size() < 1){
            log.debug("현재 이벤트가 없습니다.");
            return;
        }

        List<Queue<Long>> queueList = new ArrayList<>();

        for(EventEntity event : eventList){
            Queue<Long> queue = updateEventCouponPort.findEventQueue(event.getId());
            queueList.add(queue);
        }

        for(Queue<Long> queue : queueList){
            while (!queue.isEmpty()){
                long userId = queue.poll();

            }
        }
    }
}
