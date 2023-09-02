package com.example.order.module.domain.order.service;

import com.example.order.module.common.method.CRUDWriteService;
import com.example.order.module.domain.order.dto.OrderDto;
import com.example.order.module.domain.order.enitity.Order;
import com.example.order.module.domain.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderWriteService implements CRUDWriteService<Order, OrderDto> {

    private final OrderReadService orderReadService;

    private final OrderRepository orderRepository;

    private final ModelMapper modelMapper;

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public boolean deleteAll() {
        return false;
    }

    @Override
    public Order update(Order domain) {
        return null;
    }

    @Override
    public Order create(OrderDto createOrderDto) {
        Order order =  modelMapper.map(createOrderDto,Order.class);
        order.setCreateAt(LocalDate.now());
        order.setUpdateAt(LocalDate.now());
        Order createOrder = orderRepository.save(order);

        return createOrder;
    }


}
