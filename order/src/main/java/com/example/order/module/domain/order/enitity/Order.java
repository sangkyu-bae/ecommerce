package com.example.order.module.domain.order.enitity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Setter @Getter @EqualsAndHashCode(of ="id")
@AllArgsConstructor @NoArgsConstructor
@Table(name = "tb_order")
public class Order {

    @Id @GeneratedValue
    private Long id;

    private Long productId;

    private Long colorId;

    private Long userId;

    private int amount;

    private String address;

    private int payment;

    private LocalDate createAt;

    private LocalDate updateAt;

}
