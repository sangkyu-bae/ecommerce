package com.example.order.adapter.out.persistence.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

//주문원장
@Entity
@Getter @Setter @EqualsAndHashCode(of="id")
@AllArgsConstructor @NoArgsConstructor
@Table(name = "tb_order") @Builder
public class OrderEntity {

    @Id @GeneratedValue
    private Long id;

    private int amount;

    private int payment;

    private String address;

    private String phoneNumber;

    private LocalDateTime createAt;

    private LocalDateTime updateAt;

    private int status;

    private String aggregateIdentifier;

    @OneToMany
    private List<ProductEntity> productList;

}
