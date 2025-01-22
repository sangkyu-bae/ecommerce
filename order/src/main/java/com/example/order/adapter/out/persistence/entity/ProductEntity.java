package com.example.order.adapter.out.persistence.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

//주문 상품 원장
@Entity
@Getter @EqualsAndHashCode(of = "id")
@AllArgsConstructor @NoArgsConstructor
@Table(name = "tb_product") @Builder
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    private long productId;

    private String productName;

    private long sizeId;

    private int amount;

    private int orderAmount; //주문시 수량

    private Long couponId;

    private int salePrice; //할인합쳐진 금액

    private LocalDateTime createAt;

    private LocalDateTime updateAt;

    @ManyToOne(fetch = FetchType.LAZY)
    private OrderEntity order;

}
