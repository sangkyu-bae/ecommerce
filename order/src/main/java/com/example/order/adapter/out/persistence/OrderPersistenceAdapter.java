package com.example.order.adapter.out.persistence;

import com.example.order.adapter.out.persistence.entity.OrderEntity;
import com.example.order.adapter.out.persistence.repository.OrderEntityRepository;
import com.example.order.application.port.out.RegisterOrderPort;
import com.example.order.module.domain.order.orderentity.OrderVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class OrderPersistenceAdapter implements RegisterOrderPort {

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
}
