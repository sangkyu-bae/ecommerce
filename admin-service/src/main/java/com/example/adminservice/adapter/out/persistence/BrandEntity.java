package com.example.adminservice.adapter.out.persistence;

import com.example.adminservice.domain.product.entity.Product;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter @Setter @EqualsAndHashCode(of="id")
@AllArgsConstructor @NoArgsConstructor
@Table(name = "tb_brand")
public class BrandEntity {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private LocalDate createAt;

    private LocalDate updateAt;

    @Lob
    private String brandImage;

    @OneToMany(mappedBy = "brand")
    private List<ProductEntity> productList;
}
