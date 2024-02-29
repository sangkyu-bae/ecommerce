package com.example.adminservice.adapter.out.kafka;

import com.example.adminservice.application.port.out.SendCreateProductTaskPort;
import com.example.adminservice.application.port.out.SendFindProductTaskPort;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class RankingProducer implements SendCreateProductTaskPort, SendFindProductTaskPort {

    private final KafkaTemplate<String,String> kafkaTemplate;

    private final ObjectMapper objectMapper;

    @Value("${kafka.ranking.create.topic}")
    private String SEND_RANKING_TOPIC;

    @Value("${kafka.ranking.click.update.topic}")
    private String SEND_CLICK_UPDATE_RANKING_TOPIC;


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
            log.info("ranking send");
        }catch (JsonProcessingException e){
            log.error("fail send ranking service : {}" ,e);
        }
    }
}
