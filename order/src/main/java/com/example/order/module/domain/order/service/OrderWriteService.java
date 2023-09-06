package com.example.order.module.domain.order.service;

import com.example.order.module.common.error.ErrorException;
import com.example.order.module.common.error.errorImpl.OrderErrorCode;
import com.example.order.module.common.method.CRUDWriteService;
import com.example.order.module.domain.order.dto.OrderDto;
import com.example.order.module.domain.order.enitity.Order;
import com.example.order.module.domain.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.asm.Advice;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderWriteService implements CRUDWriteService<Order, OrderDto> {

    private final OrderReadService orderReadService;

    private final OrderRepository orderRepository;

    @Override
    public void delete(Long id) {
        if(orderRepository.existsById(id)){
            orderRepository.deleteById(id);
        }else{
            throw new ErrorException(OrderErrorCode.ORDER_NOT_FOUND,"delete");
        }
    }

    @Override
    public boolean deleteAll() {

        try{
            orderRepository.deleteAll();
            return true;
        }catch (Exception e){
            log.error("deleteAll Exception",e);
        }

        return false;
    }

    @Override
    public Order update(Order domain) {
        return null;
    }

    @Override
    public Order create(OrderDto createOrderDto) {
        Order order = orderReadService.toEntity(createOrderDto);
        order.setCreateAt(LocalDate.now());
        order.setUpdateAt(LocalDate.now());
        Order createOrder = orderRepository.save(order);

        return createOrder;
    }


}
