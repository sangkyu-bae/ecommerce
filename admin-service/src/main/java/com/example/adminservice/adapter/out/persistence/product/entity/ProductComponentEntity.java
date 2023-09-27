package com.example.adminservice.adapter.out.persistence.product.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter @Setter @EqualsAndHashCode(of="id")
@AllArgsConstructor @NoArgsConstructor
@Table(name = "tb_product_component")
public class ProductComponentEntity {

    @Id @GeneratedValue
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private ColorEntity color;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL ,mappedBy = "productComponent")
    private Set<SizeEntity> sizes;
    @ManyToOne
    private ProductEntity product;
}
