package org.example.ranking.adapter.in.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.WebAdapter;
import org.example.ranking.adapter.in.request.RegisterRankingRequest;
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
            topics = "${kafka.raking.create.topic}",
            groupId = "${kafka.raking.create.group}"
    )
    public void createRankingListener(String productIdMessage){
        try{
            RegisterRankingCommand command = RegisterRankingCommand.builder()
                    .productId(Long.parseLong(productIdMessage))
                    .build();

            Ranking registerRanking = registerRankingUseCase.registerRaking(command);
            log.info("create Ranking {}" ,registerRanking);
        }catch (Exception e){
            // 재시도는 필요없음 상품 클릭 이벤트등이 들어올시 없으면 생성
            log.error("create Raking error : {} " ,e);
        }
    }
}
