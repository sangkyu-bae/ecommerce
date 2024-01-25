package org.example.ranking.application.port.out;

import org.example.ranking.adapter.out.persistance.entity.RankingEntity;

import java.util.List;

public interface FindRankingPort {

    List<RankingEntity> findRankByClickAndLimit(int limit);
}
