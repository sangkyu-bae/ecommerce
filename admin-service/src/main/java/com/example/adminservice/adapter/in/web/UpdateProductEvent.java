package com.example.adminservice.adapter.in.web;

import com.example.adminservice.adapter.in.web.request.productRequest.CreateOrderToUpdateProductRequest;
import com.example.adminservice.application.port.in.UpdateProductUseCase;
import com.example.adminservice.application.port.in.product.CreateOrderToUpdateProductCommand;
import com.example.adminservice.common.WebAdapter;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;

@WebAdapter
@Slf4j
@RequiredArgsConstructor
public class UpdateProductEvent {
    private final ObjectMapper objectMapper;
    private final UpdateProductUseCase updateProductUseCase;
    public static final String CREATE_ORDER = "create-order";
    public static final String CREATE_ORDER_GROUP_ID = "create.order.group.v01";


    @KafkaListener(topics = CREATE_ORDER,groupId = CREATE_ORDER_GROUP_ID)
    public void orderListener(String orderMessage){
        try{
            CreateOrderToUpdateProductRequest updateProductRequest = objectMapper.readValue(orderMessage,CreateOrderToUpdateProductRequest.class);
            CreateOrderToUpdateProductCommand command = CreateOrderToUpdateProductCommand.builder()
                    .amount(updateProductRequest.getAmount())
                    .sizeId(updateProductRequest.getSizeId())
                    .orderId(updateProductRequest.getOrderId())
                    .build();

            command.validateSelf();

            updateProductUseCase.updateProductQuantity(command);

        }catch (Exception e){
            log.error("orderListener Error Message = {}",orderMessage,e);
        }
    }

}
