package com.example.order.module.domain.order;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Setter @Getter @EqualsAndHashCode(of ="id")
@AllArgsConstructor @NoArgsConstructor
public class Order {

    @Id @GeneratedValue
    private Long id;
}
