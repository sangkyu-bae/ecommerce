package com.example.order.adapter.out.persistence.repository;

import com.example.order.adapter.out.persistence.entity.OrderEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public interface OrderEntityRepository extends JpaRepository<OrderEntity,Long> {

    @Query("SELECT e FROM OrderEntity e WHERE e.userId in :memberIds")
    List<OrderEntity> findMemberOrderListByMemberIds(@Param("memberIds") List<Long> memberIds);

    List<OrderEntity> findByUserIdOrderByIdDesc(long userId);

    Page<OrderEntity> findWithPagingByUserId(Pageable pageable, long userId);
}
