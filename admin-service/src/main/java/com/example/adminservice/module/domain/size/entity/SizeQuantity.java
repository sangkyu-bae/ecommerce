package com.example.adminservice.module.domain.size.entity;

import com.example.adminservice.module.domain.quantity.entity.Quantity;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter @Setter @EqualsAndHashCode(of="id")
@AllArgsConstructor @NoArgsConstructor
public class SizeQuantity {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "size_id")
    private Size size;

    @ManyToOne
    @JoinColumn(name = "quantity_id")
    private Quantity quantity;
}
