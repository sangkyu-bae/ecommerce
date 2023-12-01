//package com.example.adminservice.adapter.in.event;
//
//import com.example.adminservice.adapter.in.event.request.RequestDeliveryRollback;
//import com.example.adminservice.application.port.in.product.RollBackOrderCommand;
//
//import lombok.RequiredArgsConstructor;
//import org.example.WebAdapter;
//import org.springframework.kafka.annotation.KafkaListener;
//
//@WebAdapter
//@RequiredArgsConstructor
//public class RollbackDeliveryConsumer {
//
//    private static final String ROLL_BACK_DELIVERY ="delivery-rollback";
//
//    private static final String ROLL_BACK_DELIVERY_GROUP = "delivery.rollback.group.v01";
//
//    @KafkaListener(topics = ROLL_BACK_DELIVERY,groupId = ROLL_BACK_DELIVERY_GROUP)
//    public void rollbackDeliveryListener(RequestDeliveryRollback request){
//        try{
//            RollBackOrderCommand command = RollBackOrderCommand.builder()
//                    .orderId(request.getOrderId())
//                    .quantity(request.getQuantity())
//                    .sizeId(request.getSizeId())
//                    .build();
//
//
//        }catch (Exception e){
//
//        }
//    }
//
//}
