package org.example.ranking.adapter.out.persistance.repository;

import org.example.ranking.adapter.out.persistance.entity.RedisRankingEntity;
import org.springframework.data.repository.CrudRepository;

public interface RedisRankingRepository extends CrudRepository<RedisRankingEntity, String> {
}
