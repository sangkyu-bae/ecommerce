package com.example.adminservice.module.domain.size.entity;

import com.example.adminservice.module.domain.product.entity.ColorProduct;
import com.example.adminservice.module.domain.quantity.entity.Quantity;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter @Setter @EqualsAndHashCode(of="id")
@AllArgsConstructor @NoArgsConstructor @Builder
public class SizeQuantity {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "size_id")
    private Size size;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quantity_id")
    private Quantity quantity;

    @ManyToOne
    private ColorProduct colorProduct;
}
