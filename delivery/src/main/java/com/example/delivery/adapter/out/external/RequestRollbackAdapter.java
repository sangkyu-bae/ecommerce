//package com.example.delivery.adapter.out.external;
//
//import com.example.delivery.application.port.in.command.RequestRollbackDeliveryCommand;
//import com.example.delivery.application.port.out.RequestRollbackPort;
//import com.example.delivery.infra.properties.AppProperties;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.example.ExternalAdapter;
//import org.springframework.kafka.core.KafkaTemplate;
//
//@ExternalAdapter
//@RequiredArgsConstructor
//@Slf4j
//public class RequestRollbackAdapter implements RequestRollbackPort {
//
//    private final KafkaTemplate<String,RequestRollbackDeliveryCommand> kafkaTemplate;
//
//    private final AppProperties appProperties;
//    @Override
//    public void rollBackDelivery(RequestRollbackDeliveryCommand command) {
//        try{
//            kafkaTemplate.send(appProperties.getDeliveryRollBack(),command);
//            log.info("rollbackSend");
//        }catch (Exception e){
//            //에러 규현 예정
//            log.error("rollbackSendFail",e);
//        }
//    }
//}
