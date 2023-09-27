package com.example.adminservice.adapter.out.persistence.product.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter @Setter @EqualsAndHashCode(of="id")
@AllArgsConstructor @NoArgsConstructor
@Table(name = "tb_product") @Builder
public class ProductEntity {

    @Id @GeneratedValue
    private Long id;

    private String name;

    private int price;

    @Lob
    private String description;

    @Lob
    private String productImage;

    @ManyToOne(fetch = FetchType.LAZY)
    private BrandEntity brand;

    @ManyToOne(fetch = FetchType.LAZY)
    private CategoryEntity category;

    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL, mappedBy = "product")
    private Set<ProductComponentEntity> productComponents;

}
