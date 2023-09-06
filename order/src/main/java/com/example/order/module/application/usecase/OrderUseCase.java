package com.example.order.module.application.usecase;

import com.example.order.infra.properties.AppProperties;
import com.example.order.module.common.kakfa.KafkaService;
import com.example.order.module.domain.order.dto.OrderDto;
import com.example.order.module.domain.order.enitity.Order;
import com.example.order.module.domain.order.service.OrderReadService;
import com.example.order.module.domain.order.service.OrderWriteService;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderUseCase {

    private final OrderReadService orderReadService;
    private final OrderWriteService orderWriteService;
    private final KafkaService kafkaService;
    public Order createOrder(OrderDto createOrderDto){
        Order order = orderWriteService.create(createOrderDto);
        kafkaService.sendToKafka(order);
        return order;
    }

}
