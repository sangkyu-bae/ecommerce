package org.example.ranking.adapter.in.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.WebAdapter;
import org.springframework.kafka.annotation.KafkaListener;

@WebAdapter
@RequiredArgsConstructor
@Slf4j
public class RankingUpdateConsumer {

    @KafkaListener(
            topics = "${kafka.update.raking.topic}",
            groupId = "${kafka.update.raking.group}"
    )
    public void createRankingListener(String clickProductId){
        try{

        }catch (Exception e){

        }
    }
}
