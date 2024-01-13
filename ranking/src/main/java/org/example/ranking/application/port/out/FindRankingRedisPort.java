package org.example.ranking.application.port.out;

import org.example.ranking.adapter.out.persistance.entity.RedisRankingEntity;
import org.example.ranking.domain.RedisRanking;

import java.util.List;

public interface FindRankingRedisPort {

    List<RedisRankingEntity> findRankingAll();

    List<RedisRanking>
}
