package com.example.delivery.module.common.consumer;

import com.example.delivery.infra.properties.AppProperties;
import com.example.delivery.module.application.usecase.DeliveryUseCase;
import com.example.delivery.module.domain.delivery.dto.DeliveryDto;
import com.example.delivery.module.domain.delivery.service.DeliveryWriteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class DeliveryKafkaConsumer {

    private final ObjectMapper objectMapper;

    private final DeliveryWriteService deliveryWriteService;
    private final DeliveryUseCase deliveryUseCase;

//    @Value("${app.orderTopic}")
//    private String topics;
//
//    @Value("${app.groupId}")
//    private String groupId;

    private final static String topics = "order";
    private final static String groupId = "delivery.group.v1";
//    @KafkaListener(topics ="#{@topics}",groupId = "#{@groupId}")
    @KafkaListener(topics =topics ,groupId = groupId)
    public void deliveryListener(String orderMessage){
        try{
            DeliveryDto deliveryDto = objectMapper.readValue(orderMessage,DeliveryDto.class);
            deliveryUseCase.createDelivery(deliveryDto);
        }catch (Exception e){
            log.error("deliveryListener Error message = {}",orderMessage,e);
        }
    }
}
