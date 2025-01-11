package com.example.order.event;

import com.example.order.adapter.out.external.delivery.DeliveryEvent;
import com.example.order.adapter.out.persistence.entity.EventStatus;
import com.example.order.application.port.out.SendCreateDeliveryEventPort;
import com.example.order.application.port.out.UpdateEventPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Slf4j
@Component
@RequiredArgsConstructor
@Async
public class SendMessageEventListener {

    private final SendCreateDeliveryEventPort sendCreateDeliveryEventPort;

    private final UpdateEventPort updateEventPort;

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void sendDeliveryEvent(DeliveryEvent event){
        try{
            sendCreateDeliveryEventPort.createDeliveryEvent(event);
            updateEventPort.updateEvent(EventStatus.SUCCESS);
        }catch (Exception e){
            log.error("이벤트 발행 실패");
            updateEventPort.updateEvent(EventStatus.FAIL);
        }
    }
}
