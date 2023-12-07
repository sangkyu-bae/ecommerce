package com.example.order.adapter.out.persistence;

import com.example.order.adapter.out.persistence.entity.OrderEntity;
import com.example.order.domain.OrderVo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderMapper {

    private final ModelMapper modelMapper;

    public OrderVo mapToDomainEntity(OrderEntity orderEntity){
        OrderVo orderVo = OrderVo.createGenerateOrderVo(
                new OrderVo.OrderId(orderEntity.getId()),
                new OrderVo.OrderProductId(orderEntity.getProductId()),
                new OrderVo.OrderColorId(orderEntity.getColorId()),
                new OrderVo.OrderSizeId(orderEntity.getSizeId()),
                new OrderVo.OrderAmount(orderEntity.getAmount()),
                new OrderVo.OrderPayment(orderEntity.getPayment()),
                new OrderVo.OrderAddress(orderEntity.getAddress()),
                new OrderVo.OrderCreateAt(orderEntity.getCreateAt()),
                new OrderVo.OrderUpdateAt(orderEntity.getUpdateAt()),
                new OrderVo.OrderStatus(orderEntity.getStatus())
        );

        return orderVo;
    }
}
