package org.example.basket.adapter.out.persistence.repository;

import org.example.basket.adapter.out.persistence.entity.BasketEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BasketRepository extends JpaRepository<BasketEntity,Long> {

    List<BasketEntity> findByMemberIdOrderByCreateAtDesc(long memberId);

}
