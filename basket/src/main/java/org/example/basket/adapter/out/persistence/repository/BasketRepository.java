package org.example.basket.adapter.out.persistence.repository;

import org.example.basket.adapter.out.persistence.entity.BasketEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BasketRepository extends JpaRepository<BasketEntity,Long> {
}
