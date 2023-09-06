package com.example.order.module.common.kakfa;

import com.example.order.infra.properties.AppProperties;
import com.example.order.module.domain.order.dto.OrderDto;
import com.example.order.module.domain.order.enitity.Order;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class KafkaService {

    private final KafkaTemplate<String,String> kafkaTemplate;
    private final ModelMapper modelMapper;
    private final ObjectMapper objectMapper;
    private final AppProperties appProperties;
    public void sendToKafka(Order order){
        try{
            OrderDto orderDto = modelMapper.map(order,OrderDto.class);
            String jsonInString = objectMapper.writeValueAsString(orderDto);
            kafkaTemplate.send(appProperties.getOrderTopic(),jsonInString);
            log.info("success sendToKafka");
        }catch (Exception e){
            log.error("sendToKafka",e);
        }
    }
}
