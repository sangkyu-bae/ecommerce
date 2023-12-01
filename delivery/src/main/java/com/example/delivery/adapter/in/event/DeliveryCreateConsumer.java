//package com.example.delivery.adapter.in.event;
//
//import com.example.delivery.adapter.in.event.request.RegisterDeliveryRequest;
//import com.example.delivery.application.port.in.command.RegisterDeliveryCommand;
//import com.example.delivery.application.port.in.command.RequestRollbackDeliveryCommand;
//import com.example.delivery.application.port.in.usecase.RegisterDeliveryUseCase;
//import com.example.delivery.application.port.in.usecase.RollbackDeliveryEventUseCase;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import lombok.RequiredArgsConstructor;
//import org.example.WebAdapter;
//import org.springframework.kafka.annotation.KafkaListener;
//
//@WebAdapter
//@RequiredArgsConstructor
//public class DeliveryCreateConsumer {
//
//    private final static String DELIVERY_CREATE_TOPIC ="delivery-topic";
//
//    private final static String DELIVERY_GROUP_ID = "delivery_group.v01";
//
//    private final RegisterDeliveryUseCase registerDeliveryUseCase;
//
//    private final RollbackDeliveryEventUseCase rollbackOrderEventUseCase;
//
//    private final ObjectMapper objectMapper;
//
//    @KafkaListener(topics = DELIVERY_CREATE_TOPIC,groupId = DELIVERY_GROUP_ID)
//    public void createDeliveryListener(String deliveryJson){
//        RegisterDeliveryRequest request = null;
//        try{
//            request = objectMapper.readValue(deliveryJson, RegisterDeliveryRequest.class);
//
//            RegisterDeliveryCommand command = RegisterDeliveryCommand.builder()
//                    .sizeId(request.getSizeId())
//                    .userId(request.getUserId())
//                    .address(request.getAddress())
//                    .orderId(request.getOrderId())
//                    .build();
//            registerDeliveryUseCase.registerDelivery(command);
//        } catch (Exception e) {
//            RequestRollbackDeliveryCommand command = RequestRollbackDeliveryCommand.builder()
//                    .orderId(request.getOrderId())
//                    .quantity(request.getQuantity())
//                    .sizeId(request.getSizeId())
//                    .build();
//
//            rollbackOrderEventUseCase.RollbackEventSend(command);
//            throw new RuntimeException(e);
//        }
//    }
//}
