package com.example.order.adapter.out.external.product;

import com.example.order.application.port.out.RequestProductInfoPort;
import com.example.order.infra.properties.AppProperties;
import com.example.order.module.domain.order.orderentity.OrderVo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductAdapter implements RequestProductInfoPort {
    private final KafkaTemplate<String,String> kafkaTemplate;
    private final ObjectMapper objectMapper;
    private final AppProperties appProperties;

    @Override
    public void createOrderEvent(ProductInfoRequest productInfoRequest) throws JsonProcessingException {
        try{
            String jsonCreateOrder = objectMapper.writeValueAsString(productInfoRequest);
            kafkaTemplate.send(appProperties.getCreateOrder(),jsonCreateOrder);
        }catch (Exception e){
            // 에러 구현 필요
            log.error("sendToKafka", e);
        }
    }
}
