package com.example.order.application.service;

import com.example.order.adapter.axon.command.OrderRemoveByEventCommand;
import com.example.order.adapter.out.persistence.entity.OrderEntity;
import com.example.order.application.port.in.command.FailRemoveOrderCommand;
import com.example.order.application.port.in.command.RemoveOrderCommand;
import com.example.order.application.port.in.usecase.RemoveOrderUseCase;
import com.example.order.application.port.out.FindOrderPort;
import com.example.order.application.port.out.GetMemberPort;
import com.example.order.application.port.out.RemoveOrderPort;
import com.example.order.application.port.out.SendRemoveOrderTaskPort;
import com.example.order.domain.OrderVo;
import com.example.order.infra.error.ErrorException;
import com.example.order.infra.error.OrderErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.UseCase;
import org.example.task.order.RemoveOrderTask;

import java.util.UUID;

@UseCase
@RequiredArgsConstructor
@Slf4j
public class RemoveOrderService implements RemoveOrderUseCase {

    private final RemoveOrderPort removeOrderPort;
    private final GetMemberPort getMemberPort;
    private final SendRemoveOrderTaskPort sendRemoveOrderTaskPort;

    private final FindOrderPort findOrderPort;

    @Override
    public void removeOrder(RemoveOrderCommand command) {
        /**
         * 1. 현재 주문 배송 상태인지 배송 서비스 확인 (구현X)
         * 2. 멤버인지 확인 (구현 O)
         * */

        /** figen 확인 */
//        boolean isMember = getMemberPort.getMember(command.getUserId());
//
//        if(!isMember){
//            throw new ErrorException(OrderErrorCode.MEMBER_NOT_FOUND,"removeOrder");
//        }

        OrderEntity orderEntity = removeOrderPort.updateRemoveOrder(
                new OrderVo.OrderId(command.getOrderId()),
                new OrderVo.OrderStatus(OrderVo.StatusCode.ORDER_REMOVE_SUCCESS.getStatus())
        );

        /**
         * 주문취소 -> 상품 수량 증가 kafka 전송 -> 실패시 saga 구현 X -> 주문 취소만 되면 사실 비즈니스에 큰 상관 X
         * 1. 상품 수량 실패시 실패 메세지 전송 받으면, 삭제 상태값만 변경
         * 2. 하루마다 스프링 배치를 통한 정합성 검증 (구현 해야함)
         * */

        RemoveOrderTask task = RemoveOrderTask.builder()
                .amount(orderEntity.getProductList().get(0).getAmount())
                .orderId(orderEntity.getId())
                .build();

        sendRemoveOrderTaskPort.removeOrderTask(task);

    }

    @Override
    public void failRemoveOrder(FailRemoveOrderCommand command) {
        log.info("orderId : "+command.getOrderId());
        removeOrderPort.updateRemoveOrder(
                new OrderVo.OrderId(command.getOrderId()),
                new OrderVo.OrderStatus(OrderVo.StatusCode.ORDER_REMOVE_FAIL.getStatus())
        );
    }

    @Override
    public void removeOrderByEvent(RemoveOrderCommand command) {
        OrderEntity orderEntity = findOrderPort.findOrder(new OrderVo.OrderId(command.getOrderId()));

        String removeOrderAggregate = UUID.randomUUID().toString();
        OrderRemoveByEventCommand axonCommand = new OrderRemoveByEventCommand(
                orderEntity.getAggregateIdentifier(),
                removeOrderAggregate,
                orderEntity.getId()
        );
    }
}
