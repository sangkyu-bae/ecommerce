package org.example.ranking.domain;

import lombok.RequiredArgsConstructor;
import org.example.ranking.application.port.in.command.UpdateRankingCommand;
import org.example.ranking.application.port.out.UpdateRankingRedisPort;

@RequiredArgsConstructor
public enum RankingEvent {
    CLICK{
        @Override
        public void updateRanking(
                UpdateRankingRedisPort updateRankingRedisPort,
                Ranking.RankingProductId rankingProductId,
                RedisRanking ranking
        ) {
            // CLICK 이벤트에 대한 구현
            updateRankingRedisPort.updateClickRankingBySortedSet(rankingProductId);
            updateRankingRedisPort.updateClickRankingView(ranking);
        }
    },
    SALE{
        @Override
        public void updateRanking(
                UpdateRankingRedisPort updateRankingRedisPort,
                Ranking.RankingProductId rankingProductId,
                RedisRanking ranking
        ) {
            // SALE 이벤트에 대한 구현
            updateRankingRedisPort.updateSaleRakingBySortedSet(rankingProductId);
            updateRankingRedisPort.updateSaleRankingView(ranking);
        }
    };


    public abstract void updateRanking(
            UpdateRankingRedisPort updateRankingRedisPort,
            Ranking.RankingProductId rankingProductId,
            RedisRanking ranking
    );
}
