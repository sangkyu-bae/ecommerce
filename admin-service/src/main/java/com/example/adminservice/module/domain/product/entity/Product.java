package com.example.adminservice.module.domain.product.entity;

import com.example.adminservice.module.domain.brand.entity.Brand;
import com.example.adminservice.module.domain.category.entity.Category;
import com.example.adminservice.module.domain.color.entity.Color;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Getter @Setter @EqualsAndHashCode(of="id")
@AllArgsConstructor @NoArgsConstructor
public class Product {

    @Id @GeneratedValue
    private Long id;

    private String name;

    private int price;

    @Lob
    private String description;

    private LocalDate createAt;

    private LocalDate updateAt;

    @Lob
    private String productImage;

    @ManyToOne
    private Brand brand;
    @ManyToOne
    private Category category;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<ColorProduct> colorProductList = new ArrayList<>();
}
