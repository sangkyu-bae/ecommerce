package org.example.coupon.adapter.out.persistence.scheduler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.coupon.adapter.out.persistence.entity.EventEntity;
import org.example.coupon.application.port.in.usecase.UpdateEventCouponUseCase;
import org.example.coupon.application.port.out.FindEventPort;
import org.example.coupon.application.port.out.UpdateEventCouponPort;
import org.example.coupon.domain.Event;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

@Slf4j
@RequiredArgsConstructor
@Component
public class QueueScheduled {

    private final UpdateEventCouponPort updateEventCouponPort;

    private final FindEventPort findEventPort;

    private final UpdateEventCouponUseCase updateEventCouponUseCase;

    @Scheduled(fixedRate = 60000)
    public void eventScheduler() {
        log.info("?>>>>>>>>>>> 실행? ");
//        updateEventCouponUseCase.process();
//        LocalDateTime now = LocalDateTime.now();
//        Event.EventStartAt eventStartAt = new Event.EventStartAt(now);
//        Event.EventEndAt eventEndAt = new Event.EventEndAt(now);
//        List<EventEntity> eventList = findEventPort.findByStartAtAfter(eventStartAt);
//
//        if(eventList.size() < 1){
//            log.debug("현재 이벤트가 없습니다.");
//            return;
//        }
//
//        List<Queue<Long>> queueList = new ArrayList<>();
//
//        for(EventEntity event : eventList){
//            log.info( "event ID : {}", event.getId());
//            Queue<Long> queue = updateEventCouponPort.findEventQueue(event.getId());
//            queueList.add(queue);
//        }
//
//        for(Queue<Long> queue : queueList){
//            while (!queue.isEmpty()){
//                long userId = queue.poll();
//
//            }
//        }
    }
}
