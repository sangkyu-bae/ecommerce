package com.example.delivery.module.domain.delivery.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Getter @Setter @EqualsAndHashCode(of = "id")
@AllArgsConstructor @NoArgsConstructor
@Table(name = "tb_delivery")
public class Delivery {

    @Id @GeneratedValue
    private Long id;

    private Long productId;

    private Long colorId;

    private Long sizeId;

    private Long userId;

    private Long orderId;

    private int amount;

    private String address;

    private LocalDate createAt;

    private LocalDate updateAt;
}
