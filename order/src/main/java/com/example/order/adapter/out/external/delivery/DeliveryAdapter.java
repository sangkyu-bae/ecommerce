package com.example.order.adapter.out.external.delivery;

import com.example.order.application.port.out.ResponseDeliveryInfoPort;
import com.example.order.common.PersistenceAdapter;
import com.example.order.common.WebAdapter;
import com.example.order.infra.properties.AppProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@WebAdapter
public class DeliveryAdapter implements ResponseDeliveryInfoPort {

    private final KafkaTemplate<String,String> kafkaTemplate;

    private final ObjectMapper objectMapper;

    private final AppProperties appProperties;
    @Override
    public void orderInformationToDelivery(OrderInfoRequest orderInfoRequest) {
        try {
            String inputOrderJson = objectMapper.writeValueAsString(orderInfoRequest);
            kafkaTemplate.send(appProperties.getOrderTopic(),inputOrderJson);
            log.info("order send");
        } catch (JsonProcessingException e) {
            log.info("fail order send",e);
        }
    }
}
