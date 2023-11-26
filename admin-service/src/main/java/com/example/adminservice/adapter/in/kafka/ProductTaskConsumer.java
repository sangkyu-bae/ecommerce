package com.example.adminservice.adapter.in.kafka;

import com.example.adminservice.application.port.in.UpdateProductUseCase;
import com.example.adminservice.application.port.in.product.CreateOrderToUpdateProductCommand;
import com.example.adminservice.infra.properties.AppProperties;
import com.example.adminservice.module.common.error.ErrorException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.task.MemberTask;
import org.example.task.OrderSubTask;
import org.example.task.ProductTask;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class ProductTaskConsumer {

    private final AppProperties appProperties;
    private final ObjectMapper objectMapper;
    private final UpdateProductUseCase updateProductUseCase;
    private final KafkaTemplate<String, String> kafkaTemplate;

    @KafkaListener(topics ="${kafka.member.topic}",groupId = "${kafka.member.task.group}")
    public void resultTaskListener(String memberTaskMessage) throws JsonProcessingException {
        ProductTask task = null;
        try{
//            task = objectMapper.readValue(memberTaskMessage, ProductTask.class);
//
//            CreateOrderToUpdateProductCommand command = CreateOrderToUpdateProductCommand.builder()
//                    .amount(task.getQuantity())
//                    .sizeId(task.getSizeId())
//                    .build();
//
//            command.validateSelf();
//
//            updateProductUseCase.updateProductQuantity(command);

//            task.setStatus(OrderSubTask.Status.SUCCESS);
        } catch (ErrorException e){
//            task.setStatus(OrderSubTask.Status.FAIL);
        } catch (Exception e){
            log.error("taskListener Error message = {}",memberTaskMessage,e);
//            task.setStatus(OrderSubTask.Status.FAIL);
        }finally {
            String inputProductTask = objectMapper.writeValueAsString(task);
            kafkaTemplate.send(appProperties.getProductTaskResultTopic(),inputProductTask);
        }
    }
}