package org.example.ranking.application.port.out;

import org.example.ranking.adapter.out.persistance.entity.RedisRankingEntity;

import java.util.List;

public interface FindRankingRedisPort {

    List<RedisRankingEntity> findRankingAll();
    List<String> findRankingByClickAndLimit(int limit);

    List<String> findRankingBySaleAndLimit(int limit);

    List<RedisRankingEntity> findRankInId(List<String> ids);

    RedisRankingEntity findRedisRankById(String key);
}
