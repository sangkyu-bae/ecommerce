package com.example.order.adapter.out.persistence;

import com.example.order.adapter.out.persistence.entity.OrderEntity;
import com.example.order.adapter.out.persistence.repository.OrderEntityRepository;
import com.example.order.application.port.out.FindOrderPort;
import com.example.order.application.port.out.GetMemberOrderPort;
import com.example.order.application.port.out.RegisterOrderPort;
import com.example.order.application.port.out.RemoveOrderPort;
import com.example.order.infra.error.ErrorException;
import com.example.order.infra.error.OrderErrorCode;
import com.example.order.domain.OrderVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.PersistenceAdapter;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@PersistenceAdapter
@RequiredArgsConstructor
@Slf4j
@Transactional
public class OrderPersistenceAdapter implements RegisterOrderPort, FindOrderPort, GetMemberOrderPort, RemoveOrderPort {

    private final OrderEntityRepository orderEntityRepository;
    @Override
    public OrderEntity createOrder(OrderVo orderVo) {

        OrderEntity createOrderEntity = OrderEntity.builder()
                .productId(orderVo.getProductId())
                .userId(orderVo.getUserId())
                .colorId(orderVo.getColorId())
                .sizeId(orderVo.getSizeId())
                .amount(orderVo.getAmount())
                .payment(orderVo.getPayment())
                .address(orderVo.getAddress())
                .status(orderVo.getStatus())
                .createAt(orderVo.getCreateAt())
                .updateAt(orderVo.getUpdateAt())
                .build();
        return orderEntityRepository.save(createOrderEntity);
    }

    @Override
    public OrderEntity findOrder(OrderVo.OrderId orderId) {
        return orderEntityRepository.findById(orderId.getId())
                .orElseThrow(()-> new ErrorException(OrderErrorCode.ORDER_NOT_FOUND,"findOrder"));
    }

    @Override
    public List<OrderEntity> findOrderByMemberId(OrderVo.OrderProductUserId userId) {
        return orderEntityRepository.findByUserIdOrderByIdDesc(userId.getUserId());
    }

    @Override
    public List<OrderEntity> getMemberOrderPort(List<Long> memberIds) {
        return orderEntityRepository.findMemberOrderListByMemberIds(memberIds);
    }

    @Override
    public OrderEntity updateRemoveOrder(OrderVo.OrderId orderId,OrderVo.OrderStatus status) {

        OrderEntity orderEntity = findOrder(orderId);
        orderEntity.setStatus(status.getStatus());

        return orderEntityRepository.save(orderEntity);
    }
}
