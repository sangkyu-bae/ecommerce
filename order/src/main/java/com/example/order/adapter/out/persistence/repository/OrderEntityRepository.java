package com.example.order.adapter.out.persistence.repository;

import com.example.order.adapter.out.persistence.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public interface OrderEntityRepository extends JpaRepository<OrderEntity,Long> {

    @Query("SELECT e FROM OrderEntity e WHERE e.id in :meberIds")
    List<OrderEntity> findMemberOrderListByMemberIds(@Param("memberIds") List<Long> memberIds);

    Optional <OrderEntity> findByIdAndUserId(long orderId, long userId);
}
