package com.example.adminservice.domain.brand.entity;

import com.example.adminservice.adapter.out.persistence.Product;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter @Setter @EqualsAndHashCode(of="id")
@AllArgsConstructor @NoArgsConstructor
@ToString(exclude = "productList")
public class Brand {

    @Id @GeneratedValue
    private Long id;

    private String name;

    private LocalDate createAt;

    private LocalDate updateAt;

    @Lob
    private String brandImage;

    @JsonBackReference
    @OneToMany(mappedBy = "brand")
    private List<Product> productList;
}
