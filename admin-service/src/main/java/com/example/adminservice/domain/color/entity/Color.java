package com.example.adminservice.domain.color.entity;

import com.example.adminservice.domain.product.entity.ColorProduct;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter @Setter @EqualsAndHashCode(of="id")
@AllArgsConstructor @NoArgsConstructor
public class Color {

    @Id @GeneratedValue
    private Long id;

    private String name;

    @OneToMany(mappedBy = "color")
    private List<ColorProduct> colorProductList;
}
