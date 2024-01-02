package com.example.delivery.adapter.out.persistance.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Getter @Setter @EqualsAndHashCode(of = "id")
@AllArgsConstructor @NoArgsConstructor
@Table(name = "tb_delivery") @Builder
public class DeliveryEntity {

    @Id @GeneratedValue
    private Long id;

    private Long sizeId;

    private Long userId;

    private Long orderId;

    private String address;

    private int status;

    private LocalDate createAt;

    private LocalDate updateAt;

}
