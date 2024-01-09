package org.example.ranking.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.UseCase;
import org.example.ranking.application.port.in.command.UpdateClickRankingCommand;
import org.example.ranking.application.port.in.usecase.UpdateRankingUseCase;
import org.example.ranking.application.port.out.UpdateRankingRedisPort;
import org.example.ranking.domain.Ranking;
import org.example.ranking.domain.RedisRanking;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@Slf4j
@RequiredArgsConstructor
@Transactional
public class UpdateRankingService implements UpdateRankingUseCase {

    private final UpdateRankingRedisPort updateRankingPort;

    @Override
    public void updateClickRanking(UpdateClickRankingCommand command) {
        updateRankingPort.updateClickRankingBySortedSet(
                new Ranking.RankingProductName(command.getProductName())
        );

        RedisRanking ranking = RedisRanking.createGenerateRedisRanking(
                new RedisRanking.RedisRankingProductName(command.getProductName()),
                new RedisRanking.RedisRankingProductId(command.getProductId()),
                null
        );

        updateRankingPort.updateClickRankingView(ranking);
    }


}
