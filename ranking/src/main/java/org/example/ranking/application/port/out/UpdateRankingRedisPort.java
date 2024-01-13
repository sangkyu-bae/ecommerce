package org.example.ranking.application.port.out;

import org.example.ranking.domain.Ranking;
import org.example.ranking.domain.RedisRanking;

public interface UpdateRankingRedisPort {

    void updateClickRankingBySortedSet(Ranking.RankingProductName rankingProductName);

    void updateSaleRakingBySortedSet(Ranking.RankingProductName rankingProductName);
    void updateClickRankingView(RedisRanking redisRanking);

    void updateSaleRankingView(RedisRanking redisRanking);
}
