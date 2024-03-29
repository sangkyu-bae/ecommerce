package org.example.ranking.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.UseCase;
import org.example.ranking.application.port.in.command.UpdateRankingCommand;
import org.example.ranking.application.port.in.usecase.UpdateRankingUseCase;
import org.example.ranking.application.port.out.UpdateRankingRedisPort;
import org.example.ranking.domain.Ranking;
import org.example.ranking.domain.RankingEvent;
import org.example.ranking.domain.RedisRanking;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@Slf4j
@RequiredArgsConstructor
@Transactional
public class UpdateRankingService implements UpdateRankingUseCase {

    private final UpdateRankingRedisPort updateRankingPort;

    @Override
    public void updateRanking(UpdateRankingCommand command, RankingEvent rankingEvent){

        RedisRanking ranking = RedisRanking.createGenerateRedisRanking(
                new RedisRanking.RedisRankingProductId(command.getProductId()),
                new RedisRanking.RedisRankingProductName(command.getProductName()),
                new RedisRanking.RedisRankingScore(null)
        );

        rankingEvent.updateRanking(
                updateRankingPort
                ,new Ranking.RankingProductId(command.getProductId())
                ,ranking
        );
    }

}
