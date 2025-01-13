package com.example.order.adapter.out.external.product;

import com.example.order.adapter.axon.command.OrderRequestCreateCommand;
import com.example.order.application.port.in.command.RegisterOrderCommand;
import com.example.order.application.port.out.SendAxonOrderPort;
import com.example.order.domain.OrderVo;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.example.PersistenceAdapter;

import java.util.UUID;

@PersistenceAdapter
@RequiredArgsConstructor
@Slf4j
public class AxonProductAdapter implements SendAxonOrderPort {
    private final CommandGateway commandGateway;
    @Override
    public void sendOrderWithSaga(RegisterOrderCommand command) {
        /**
         * TODO
         * event 소싱후 result값에서 주문관련 오류가 생겼거나
         * 에러 발생시 상품 저장된 이후 롤백 정책 추가 필요
         * */

        String orderAggregateIdentifier = UUID.randomUUID().toString();
        OrderRequestCreateCommand axonCommand = new OrderRequestCreateCommand(
                orderAggregateIdentifier,
                command.getProductId(),
                command.getColorId(),
                command.getSizeId(),
                command.getAmount(),
                command.getPayment(),
                command.getAddress(),
                OrderVo.StatusCode.ORDER.getStatus(),
                command.getUserId(),
                command.getCouponId()
        );

        command.setAggregateIdentifier(orderAggregateIdentifier);

        commandGateway.send(command).whenComplete((result, throwable) -> {
            if (throwable != null) {
                log.error("throwable = " + throwable);
                throw new RuntimeException(throwable);
            } else{
                System.out.println("result = " + result);
            }
        });

    }
}
