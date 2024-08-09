package org.example.coupon.adapter.out.persistence.scheduler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.coupon.adapter.out.persistence.entity.EventEntity;
import org.example.coupon.application.port.out.FindEventPort;
import org.example.coupon.application.port.out.UpdateEventCouponPort;
import org.example.coupon.domain.Event;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

@Slf4j
@RequiredArgsConstructor
public class QueueScheduled {

    private final UpdateEventCouponPort updateEventCouponPort;

    private final FindEventPort findEventPort;

    @Scheduled(fixedRate = 1000)
    public void eventScheduler() {
        LocalDateTime now = LocalDateTime.now();
        Event.EventStartAt eventStartAt = new Event.EventStartAt(now);
        Event.EventEndAt eventEndAt = new Event.EventEndAt(now);
        List<EventEntity> eventList = findEventPort.findByStartAtAfter(eventStartAt);

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
