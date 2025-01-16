package com.example.order.adapter.out.external.product;

import com.example.order.adapter.axon.command.OrderRequestCreateCommand;
import com.example.order.adapter.out.persistence.entity.EventEntity;
import com.example.order.adapter.out.persistence.entity.EventStatus;
import com.example.order.adapter.out.persistence.repository.EventEntityRepository;
import com.example.order.application.port.in.command.RegisterOrderCommand;
import com.example.order.application.port.out.SendAxonOrderPort;
import com.example.order.domain.OrderVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.example.PersistenceAdapter;
import org.modelmapper.ModelMapper;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@PersistenceAdapter
@RequiredArgsConstructor
@Slf4j
public class AxonProductAdapter implements SendAxonOrderPort {
    private final CommandGateway commandGateway;

    private final EventEntityRepository eventEntityRepository;

    private final ModelMapper modelMapper;
    @Override
    @Transactional
    public void sendOrderWithSaga(RegisterOrderCommand command,OrderVo.OrderId orderId,String eventId) {
        /**
         * TODO
         * event 소싱후 result값에서 주문관련 오류가 생겼거나
         * 에러 발생시 상품 저장된 이후 롤백 정책 추가 필요
         * */

        String orderAggregateIdentifier = command.getAggregateIdentifier();

        List<OrderRequestCreateCommand.ProductRequestCommand> productRequestCommands = command.getProductCommands().stream()
                .map(product->modelMapper.map(product, OrderRequestCreateCommand.ProductRequestCommand.class))
                .collect(Collectors.toList());

        OrderRequestCreateCommand axonCommand = new OrderRequestCreateCommand(
                orderAggregateIdentifier,
                command.getPayment(),
                command.getAddress(),
                OrderVo.StatusCode.ORDER.getStatus(),
                command.getUserId(),
                productRequestCommands
        );

        commandGateway.send(axonCommand).whenComplete((result, throwable) -> {
            if (throwable != null) {
                log.error("throwable = " + throwable);
                EventEntity eventEntity = eventEntityRepository.findById(eventId).orElseThrow(()->new IllegalArgumentException(""));
                eventEntity.updateStatus(EventStatus.FAIL_WORK);
                throw new RuntimeException(throwable);
            } else{
                System.out.println("result = " + result);
            }
        });

    }
}
