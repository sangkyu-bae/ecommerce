package com.example.adminservice.adapter.out.kafka;

import com.example.adminservice.application.port.in.command.FindProductCommand;
import com.example.adminservice.application.port.out.product.SendCreateProductTaskPort;
import com.example.adminservice.application.port.out.product.SendFindProductTaskPort;
import com.example.adminservice.domain.ProductVo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class ProductProducer implements SendCreateProductTaskPort, SendFindProductTaskPort {

    private final KafkaTemplate<String,String> kafkaTemplate;

    private final ObjectMapper objectMapper;

    @Value("${kafka.ranking.create.topic}")
    private String SEND_RANKING_TOPIC;

    @Value("${kafka.ranking.click.update.topic}")
    private String SEND_CLICK_UPDATE_RANKING_TOPIC;

    @Value("${kafka.elk.click.topic}")
    private String SEND_CLICK_PRODUCT_TOPIC;

    private static final Logger logger = LoggerFactory.getLogger("elk");


    @Override
    public void sendCreateProductTask(long productId) {
        try{
            String inputProductIdJson = objectMapper.writeValueAsString(productId);
            kafkaTemplate.send(SEND_RANKING_TOPIC,inputProductIdJson);
            log.info("ranking send");
        }catch (JsonProcessingException e){
            log.error("fail send ranking service : {}" ,e);
        }
    }

    @Override
    public void sendFindProductTask(long productId,String productName) {
        try{
            UpdateRankingTask task = new UpdateRankingTask(productName,productId);
            String inputProductIdJson = objectMapper.writeValueAsString(task);
            kafkaTemplate.send(SEND_CLICK_UPDATE_RANKING_TOPIC,inputProductIdJson);
            logger.info("ranking send");
        }catch (JsonProcessingException e){
            logger.error("fail send ranking service : {}" ,e);
        }
    }

    @Override
    public void sendFindProductTaskToELK(ProductVo product) {

        SendProductTask sendProductTask = SendProductTask.builder()
                .brandName(product.getBrand().getName())
                .productName(product.getName())
                .productId(product.getId())
                .type(product.getCategory().getName())
                .productImage(product.getProductImage())
                .build();
        try{
            String sendProductJson = objectMapper.writeValueAsString(sendProductTask);
            kafkaTemplate.send(SEND_CLICK_PRODUCT_TOPIC,sendProductJson);
            logger.info("click send product : {}", sendProductTask);
        }catch (JsonProcessingException e){
            logger.error("fail send product : {}", e.getStackTrace());
        }
    }
}
