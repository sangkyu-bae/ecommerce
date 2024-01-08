package org.example.ranking.application.port.out;

import org.example.ranking.domain.Ranking;

public interface UpdateRankingRedisPort {

    void updateClickRanking(Ranking.RankingProductName rankingProductName);
}
