package org.example.ranking.adapter.out.persistance.repository;

import org.example.ranking.adapter.out.persistance.entity.RankingEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RankingRepository extends JpaRepository<RankingEntity,Long> {

    @Query("select r from RankingEntity r order by r.clickNum desc")
    List<RankingEntity> findWithPagingOrderByClickNum(Pageable pageable);
}
