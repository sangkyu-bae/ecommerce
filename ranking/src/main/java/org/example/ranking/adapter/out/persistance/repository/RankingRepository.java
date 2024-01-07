package org.example.ranking.adapter.out.persistance.repository;

import org.example.ranking.adapter.out.persistance.entity.RankingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RankingRepository extends JpaRepository<RankingEntity,Long> {
}
