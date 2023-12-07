package com.example.adminservice.adapter.out.external;


import com.example.adminservice.adapter.in.request.DeliveryInfoRequest;
import com.example.adminservice.application.port.out.RequestDeliveryPort;
import com.example.adminservice.infra.properties.AppProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.PersistenceAdapter;
import org.springframework.kafka.core.KafkaTemplate;

@PersistenceAdapter
@RequiredArgsConstructor
@Slf4j
public class DeliveryAdaptor implements RequestDeliveryPort {

    private final KafkaTemplate<String,String> kafkaTemplate;
    private final ObjectMapper objectMapper;
    private final AppProperties appProperties;

    @Override
    public void sendCreateOrderEvent(DeliveryInfoRequest deliveryInfoRequest) {
        try{
            String jsonCreateDelivery = objectMapper.writeValueAsString(deliveryInfoRequest);
            kafkaTemplate.send(appProperties.getCreateDelivery(),jsonCreateDelivery);
        }catch (Exception e){
            // 에러 구현 필요
            log.error("sendToKafka", e);
        }
    }
}
