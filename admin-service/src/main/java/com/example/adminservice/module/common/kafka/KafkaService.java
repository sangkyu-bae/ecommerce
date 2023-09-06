package com.example.adminservice.module.common.kafka;

import com.example.adminservice.infra.properties.AppProperties;
import com.example.adminservice.module.domain.product.OrderDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaService {
    private final KafkaTemplate<String,String> kafkaTemplate;
    private final ModelMapper modelMapper;
    private final ObjectMapper objectMapper;
    private final AppProperties appProperties;

    public void sendToKafka(OrderDto orderDto){
        try{
            String jsonInString = objectMapper.writeValueAsString(orderDto);
            kafkaTemplate.send(appProperties.getOrderTopic(),jsonInString);
            log.info("success sendToKafka");
        }catch (Exception e){
            log.error("sendToKafka",e);
        }
    }

}
