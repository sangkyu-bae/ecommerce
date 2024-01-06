package com.example.order.adapter.out.persistence.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Getter @Setter @EqualsAndHashCode(of="id")
@AllArgsConstructor @NoArgsConstructor
@Table(name = "tb_order") @Builder
public class OrderEntity {

    @Id @GeneratedValue
    private Long id;

    private long productId;

    private long userId;

    private long colorId;

    private long sizeId;

    private int amount;

    private int payment;

    private String address;

    private LocalDate createAt;

    private LocalDate updateAt;

    private int status;

    private String aggregateIdentifier;

}
