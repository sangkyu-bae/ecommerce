package org.example.ranking.adapter.in.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.WebAdapter;
import org.example.ranking.adapter.in.request.UpdateRankingTask;
import org.example.ranking.application.port.in.command.UpdateRankingCommand;
import org.example.ranking.application.port.in.usecase.UpdateRankingUseCase;
import org.example.ranking.domain.RankingEvent;
import org.springframework.kafka.annotation.KafkaListener;

@WebAdapter
@RequiredArgsConstructor
@Slf4j
public class RankingUpdateConsumer {

    private final UpdateRankingUseCase updateRankingUseCase;

    private final ObjectMapper objectMapper;

    @KafkaListener(
            topics = "${kafka.raking.update.click.topic}",
            groupId = "${kafka.raking.update.click.group}"
    )
    public void updateClickRankingListener(String updateRankingMessage){
        UpdateRankingTask task = null;
        try{
            task = objectMapper.readValue(updateRankingMessage, UpdateRankingTask.class);

            UpdateRankingCommand command = UpdateRankingCommand.builder()
                    .productId(task.getProductId())
                    .productName(task.getProductName())
                    .build();

            updateRankingUseCase.updateRanking(command, RankingEvent.CLICK);
            log.info("raking update success");
        }catch (Exception e){
            log.error("raking Click update Error : {}",e);
        }
    }
}
