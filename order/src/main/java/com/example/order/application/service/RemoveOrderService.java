package com.example.order.application.service;

import com.example.order.adapter.out.persistence.entity.OrderEntity;
import com.example.order.application.port.in.command.RemoveOrderCommand;
import com.example.order.application.port.in.usecase.RemoveOrderUseCase;
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

@UseCase
@RequiredArgsConstructor
@Slf4j
public class RemoveOrderService implements RemoveOrderUseCase {

    private final RemoveOrderPort removeOrderPort;
    private final GetMemberPort getMemberPort;

    private final SendRemoveOrderTaskPort sendRemoveOrderTaskPort;

    @Override
    public void removeOrder(RemoveOrderCommand command) {
        /**
         * 1. 현재 주문 배송 상태인지 배송 서비스 확인 (구현X)
         * 2. 멤버인지 확인 (구현 O)
         * */
        boolean isMember = getMemberPort.getMember(command.getUserId());

        if(!isMember){
            throw new ErrorException(OrderErrorCode.MEMBER_NOT_FOUND,"removeOrder");
        }

        OrderEntity orderEntity = removeOrderPort.removeOrder(
                new OrderVo.OrderId(command.getOrderId())
        );

        /**
         * 주문취소 -> 상품 수량 증가 kafka 전송 -> 실패시 saga 구현 X
         * 1. 상품 수량 실패시 실패 메세지 전송 받으면, 삭제 상태값만 변경
         * 2. 하루마다 스프링 배치를 통한 정합성 검증 (구현 해야함)
         * */

        RemoveOrderTask task = RemoveOrderTask.builder()
                .sizeId(orderEntity.getSizeId())
                .productId(orderEntity.getProductId())
                .amount(orderEntity.getAmount())
                .build();

        sendRemoveOrderTaskPort.removeOrderTask(task);

    }
}
