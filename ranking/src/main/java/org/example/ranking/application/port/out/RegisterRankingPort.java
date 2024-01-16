package org.example.ranking.application.port.out;

import org.example.ranking.adapter.out.persistance.entity.RankingEntity;
import org.example.ranking.domain.Ranking;

import java.util.List;

public interface RegisterRankingPort {
    RankingEntity registerRanking(Ranking ranking);

    List<RankingEntity> bulkRegisterRanking(List<Ranking> rankingList);
}
