package com.example.adminservice.adapter.out.persistence.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter @Setter @EqualsAndHashCode(of="id")
@AllArgsConstructor @NoArgsConstructor
@Table(name = "tb_category") @Builder
public class CategoryEntity {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @OneToMany(mappedBy = "category",fetch = FetchType.LAZY)
    private List<ProductEntity> productList;
}
