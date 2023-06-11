package com.example.adminservice.module.domain.product.entity;

import com.example.adminservice.module.domain.color.entity.Color;
import com.example.adminservice.module.domain.size.entity.Size;
import com.example.adminservice.module.domain.size.entity.SizeQuantity;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter @Setter @EqualsAndHashCode(of="id")
@AllArgsConstructor @NoArgsConstructor
public class ColorProduct {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "color_id")
    private Color color;

    @ManyToOne
    @JoinColumn(name="product_id")
    private Product product;

    @OneToMany
    private List<SizeQuantity> sizeList;
}
