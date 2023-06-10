package com.example.adminservice.module.domain.brand.entity;

import com.example.adminservice.module.domain.product.entity.Product;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter @Setter @EqualsAndHashCode(of="id")
@AllArgsConstructor @NoArgsConstructor
public class Brand {

    @Id @GeneratedValue
    private Long id;

    private String name;

    private LocalDate createAt;

    private LocalDate updateAt;

    @Lob
    private String brandImage;

    @OneToMany
    private List<Product> product;
}
