package com.example.order.event;

import com.example.order.adapter.out.persistence.EventMapper;
import com.example.order.adapter.out.persistence.OrderMapper;
import com.example.order.adapter.out.persistence.entity.OrderEntity;
import com.example.order.application.port.in.command.RegisterOrderCommand;
import com.example.order.application.port.out.RegisterEventPort;
import com.example.order.application.port.out.RegisterOrderPort;
import com.example.order.application.port.out.SendAxonOrderPort;
import com.example.order.domain.Event;
import com.example.order.domain.OrderVo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.JsonConverter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import java.time.LocalDateTime;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class CreateEventListener {

    private final SendAxonOrderPort sendAxonOrderPort;
    private final RegisterEventPort registerEventPort;
    private final RegisterOrderPort registerOrderPort;
    private final ObjectMapper objectMapper;
    private final JsonConverter jsonConverter;
    private final EventMapper eventMapper;
    private final OrderMapper orderMapper;

    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void createEvent(CreateOrderEventCommand command){

        //상품을 등록한다.
        int status = OrderVo.StatusCode.ORDER.getStatus();
        OrderVo registerOrder = createOrder(command.getRegisterOrderCommand(),status);
        String eventId = registerOrder.getAggregateIdentifier();
        //사가 이벤트를 소싱한다.
        sendAxonOrderPort.sendOrderWithSaga(
                command.getRegisterOrderCommand()
                ,new OrderVo.OrderId(registerOrder.getId())
                ,eventId
        );

        //이벤트를 발생하여 트랜잭션 아웃박스를 시작한다.
        Event registerEvent = createEvent(command.getEventCommand());
    }

    private Event createEvent(EventCommand eventCommand) {
        String jsonEventData = "";

        try {
            jsonEventData=  objectMapper.writeValueAsString(eventCommand.getEventData());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("변환 오류");
        }
        Event event = Event.createGenerate(
                null
                ,eventCommand.getEventStatus()
                ,eventCommand.getEventType()
                ,jsonConverter.convertToEntityAttribute(jsonEventData)
        );
       return  eventMapper.mapToDomainEntity(registerEventPort.registerEvent(event));
    }

    private OrderVo createOrder(RegisterOrderCommand command,int status){
        OrderVo createOrder = OrderVo.createGenerateOrderVo(
                new OrderVo.OrderId(0),
                new OrderVo.OrderProductUserId(command.getUserId()),
                new OrderVo.OrderPayment(command.getPayment()),
                new OrderVo.OrderAddress(command.getAddress()),
                new OrderVo.OrderCreateAt(LocalDateTime.now()),
                new OrderVo.OrderUpdateAt(LocalDateTime.now()),
                new OrderVo.OrderStatus(status),
                OrderVo.StatusCode.ORDER,
                new OrderVo.OrderAggregateIdentifier(command.getAggregateIdentifier())
        );

        OrderEntity createOrderEntity = registerOrderPort.createOrder(createOrder);
        return orderMapper.mapToDomainEntity(createOrderEntity);
    }

}
