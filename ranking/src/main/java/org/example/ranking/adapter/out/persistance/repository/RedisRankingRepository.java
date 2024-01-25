package org.example.ranking.adapter.out.persistance.repository;

import org.example.ranking.adapter.out.persistance.entity.RedisRankingEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RedisRankingRepository extends CrudRepository<RedisRankingEntity, String> {
    List<RedisRankingEntity> findAllById(List<String> ids);
}
