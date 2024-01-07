package org.example.ranking.adapter.in.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.WebAdapter;
import org.example.ranking.application.port.in.command.RegisterRankingCommand;
import org.example.ranking.application.port.in.usecase.RegisterRankingUseCase;
import org.example.ranking.domain.Ranking;
import org.springframework.kafka.annotation.KafkaListener;

@WebAdapter
@RequiredArgsConstructor
@Slf4j
public class RankingCreateConsumer {

    private final RegisterRankingUseCase registerRankingUseCase;
    @KafkaListener(
            topics = "${kafka.insert.raking.topic}",
            groupId = "${kafka.insert.raking.group}"
    )
    public void createRankingListener(long productId){
        try{
            RegisterRankingCommand command = RegisterRankingCommand.builder()
                    .productId(productId)
                    .build();

            Ranking registerRanking = registerRankingUseCase.registerRaking(command);

            log.info("create Ranking {}" ,registerRanking);
        }catch (Exception e){
            log.error("create Raking error : {} " ,e);
        }

    }
}
