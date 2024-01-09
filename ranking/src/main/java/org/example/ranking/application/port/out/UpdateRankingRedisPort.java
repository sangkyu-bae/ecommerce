package org.example.ranking.application.port.out;

import org.example.ranking.domain.Ranking;
import org.example.ranking.domain.RedisRanking;

public interface UpdateRankingRedisPort {

    void updateClickRankingBySortedSet(Ranking.RankingProductName rankingProductName);

//    void updateClickRankingView(Ranking.RankingProductId rankingProductId);
    void updateClickRankingView(RedisRanking ranking);
}
