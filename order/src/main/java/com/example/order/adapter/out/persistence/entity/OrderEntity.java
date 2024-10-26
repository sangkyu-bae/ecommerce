package com.example.order.adapter.out.persistence.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;

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

    private String phoneNumber;

    private LocalDateTime createAt;

    private LocalDateTime updateAt;

    private int status;

    private String sequence;

    private String aggregateIdentifier;

}
