package com.example.adminservice.module.domain.product.entity;

import com.example.adminservice.module.domain.color.entity.Color;
import com.example.adminservice.module.domain.size.entity.Size;
import com.example.adminservice.module.domain.size.entity.SizeQuantity;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter @EqualsAndHashCode(of="id")
@AllArgsConstructor @NoArgsConstructor @Builder
public class ColorProduct {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "color_id")
    private Color color;

    @ManyToOne
    @JoinColumn(name="product_id")
    private Product product;

    @OneToMany(mappedBy = "colorProduct", cascade = CascadeType.ALL)
    private List<SizeQuantity> sizeList;

    public ColorProduct(Color color,Product product){
        this.color = color;
        this.product = product;
    }
}
