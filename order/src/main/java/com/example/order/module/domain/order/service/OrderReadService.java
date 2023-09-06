package com.example.order.module.domain.order.service;

import com.example.order.module.common.method.CRUDReadService;
import com.example.order.module.domain.order.dto.OrderDto;
import com.example.order.module.domain.order.enitity.Order;
import com.example.order.module.domain.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderReadService implements CRUDReadService<Order, OrderDto> {
    private final OrderRepository orderRepository;

    private final ModelMapper modelMapper;
    @Override
    public Order read(Long id) {
        return null;
    }

    @Override
    public List<Order> readAll() {
        return null;
    }

    @Override
    public Order toEntity(OrderDto orderDto) {
        return modelMapper.map(orderDto,Order.class);
    }
}
