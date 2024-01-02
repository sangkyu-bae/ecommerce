
package com.example.delivery.adapter.in.kafka;

import com.example.delivery.adapter.in.kafka.request.RegisterDeliveryEvent;
import com.example.delivery.adapter.in.kafka.request.RegisterDeliveryRequest;
import com.example.delivery.application.port.in.command.RegisterDeliveryCommand;
import com.example.delivery.application.port.in.command.RequestRollbackDeliveryCommand;
import com.example.delivery.application.port.in.usecase.RegisterDeliveryUseCase;
import com.example.delivery.application.port.in.usecase.RollbackDeliveryEventUseCase;

import com.example.delivery.domain.DeliveryVo;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.WebAdapter;
import org.springframework.kafka.annotation.KafkaListener;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@WebAdapter
@RequiredArgsConstructor
@Slf4j
public class DeliveryCreateConsumer {

    private final static String DELIVERY_CREATE_TOPIC = "delivery-topic";

    private final static String DELIVERY_GROUP_ID = "delivery_group.v01";

    private final RegisterDeliveryUseCase registerDeliveryUseCase;

    private final RollbackDeliveryEventUseCase rollbackOrderEventUseCase;

    private final ObjectMapper objectMapper;

    private static int i = 0;
    long startTime = System.currentTimeMillis();
    @KafkaListener(
            topics = DELIVERY_CREATE_TOPIC,
            groupId = DELIVERY_GROUP_ID,
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void createDeliveryListener(String deliveryJson) {
        RegisterDeliveryRequest request = null;
        try {


            request = objectMapper.readValue(deliveryJson, RegisterDeliveryRequest.class);

            RegisterDeliveryCommand command = RegisterDeliveryCommand.builder()
                    .sizeId(request.getSizeId())
                    .userId(Long.valueOf(request.getUserId()))
                    .address(request.getAddress())
                    .orderId(request.getOrderId())
                    .build();
            registerDeliveryUseCase.registerDelivery(command);

            i++;
            System.out.println(i);
            if(i == 100){
                long endTime = System.currentTimeMillis();
                long dbInsertTime = endTime - startTime;
                log.info("insertTime : {}", dbInsertTime);
            }

        } catch (Exception e) {
//            RequestRollbackDeliveryCommand command = RequestRollbackDeliveryCommand.builder()
//                    .orderId(request.getOrderId())
//                    .quantity(request.getQuantity())
//                    .sizeId(request.getSizeId())
//                    .build();
//
//            rollbackOrderEventUseCase.RollbackEventSend(command);
            log.error("error request {}", request);
            throw new RuntimeException(e);
        }
    }

    @KafkaListener(topics = "${kafka.bulk.insert.delivery.topic}",
            groupId = "${kafka.bulk.insert.delivery.group}",
            containerFactory = "batchKafkaListenerContainerFactory")
    public void createDeliveryListenerByBulkInsert(List<RegisterDeliveryEvent> deliveryJsonList) {
        log.info("deliveryJsonList : {}", deliveryJsonList);
        log.info("size {}", deliveryJsonList.size());
        try {

            List<RegisterDeliveryCommand> commands = deliveryJsonList.stream()
                    .map(request -> RegisterDeliveryCommand.builder()
                            .userId(request.getUserId())
                            .address(request.getAddress())
                            .orderId(request.getOrderId())
                            .build())
                    .collect(Collectors.toList());

            List<DeliveryVo> insertDeliveryVoList =registerDeliveryUseCase.bulkRegisterDelivery(commands);

            log.info("bulk insert delivery Info : {}", insertDeliveryVoList);

            long endTime = System.currentTimeMillis();
            long dbInsertTime = endTime - startTime;
            log.info("insertTime : {}", dbInsertTime);
        } catch (Exception e) {
            log.error("Error occurred while parsing JSON: {}", e.getMessage());
            log.error("data {}" ,deliveryJsonList);
        }
    }
}
