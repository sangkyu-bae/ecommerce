package com.example.order.adapter.out.persistence;

import com.example.order.adapter.out.persistence.entity.OrderEntity;
import com.example.order.adapter.out.persistence.repository.OrderEntityRepository;
import com.example.order.application.port.out.FindOrderPort;
import com.example.order.application.port.out.GetMemberOrderPort;
import com.example.order.application.port.out.RegisterOrderPort;
import com.example.order.module.common.error.ErrorException;
import com.example.order.module.common.error.errorImpl.OrderErrorCode;
import com.example.order.module.domain.order.enitity.Order;
import com.example.order.module.domain.order.orderentity.OrderVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.PersistenceAdapter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@PersistenceAdapter
@RequiredArgsConstructor
@Slf4j
@Transactional
public class OrderPersistenceAdapter implements RegisterOrderPort, FindOrderPort, GetMemberOrderPort {

    private final OrderEntityRepository orderEntityRepository;
    @Override
    public OrderEntity createOrder(OrderVo orderVo) {

        OrderEntity createOrderEntity = OrderEntity.builder()
                .productId(orderVo.getProductId())
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
    public List<OrderEntity> getMemberOrderPort(List<Long> memberIds) {
        return orderEntityRepository.findMemberOrderListByMemberIds(memberIds);
    }
}
