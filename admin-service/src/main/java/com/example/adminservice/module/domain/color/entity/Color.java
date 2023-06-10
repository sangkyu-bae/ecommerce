package com.example.adminservice.module.domain.color.entity;

import com.example.adminservice.module.domain.product.entity.ColorProduct;
import com.example.adminservice.module.domain.size.dto.Size;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Getter @Setter @EqualsAndHashCode(of="id")
@AllArgsConstructor @NoArgsConstructor
public class Color {

    @Id @GeneratedValue
    private Long id;

    private String name;

    @OneToMany
    private Set<ColorProduct> colorProductSet;
}
