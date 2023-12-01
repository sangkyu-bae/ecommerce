package com.example.order.module.domain.order.service;

import com.example.order.module.common.error.ErrorException;
import com.example.order.module.common.error.errorImpl.OrderErrorCode;
import com.example.order.module.common.method.CRUDWriteService;
import com.example.order.module.domain.order.dto.OrderDto;
import com.example.order.module.domain.order.enitity.Order;
import com.example.order.module.domain.order.repository.OrderRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.asm.Advice;
import org.modelmapper.ModelMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderWriteService implements CRUDWriteService<Order, OrderDto> {

    private final OrderReadService orderReadService;

    private final OrderRepository orderRepository;
    private final ObjectMapper objectMapper;

    private final static String topics = "order";
    private final static String groupId = "delivery.group.v13";

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

//    @KafkaListener(topics = topics,groupId = groupId)
//    public void orderListener(String orderMessage){
//        try{
//            OrderDto orderDto = objectMapper.readValue(orderMessage,OrderDto.class);
//            create(orderDto);
//        }catch (Exception e){
//            log.error("orderListener Error Message = {}",orderMessage,e);
//        }
//    }
}
