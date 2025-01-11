package com.example.order.event;

import com.example.order.application.port.out.RegisterEventPort;
import com.example.order.domain.Event;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Slf4j
@Component
@RequiredArgsConstructor
//@EventHandler
public class CreateEventListener {

    private final RegisterEventPort registerEventPort;

    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void createEvent(EventCommand command){
        Event<?> event = Event.createGenerate(null,null,null);
        registerEventPort.registerEvent(event);
    }

}
