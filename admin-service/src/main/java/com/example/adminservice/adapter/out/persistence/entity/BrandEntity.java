package com.example.adminservice.adapter.out.persistence.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter @Setter @EqualsAndHashCode(of="id")
@AllArgsConstructor @NoArgsConstructor
@Table(name = "tb_brand") @Builder
public class BrandEntity {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private LocalDate createAt;

    private LocalDate updateAt;

    @Lob
    private String brandImage;

    @OneToMany(mappedBy = "brand",fetch = FetchType.LAZY)
    private List<ProductEntity> productList;
}
