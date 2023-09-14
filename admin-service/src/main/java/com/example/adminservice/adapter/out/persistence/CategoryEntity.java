package com.example.adminservice.adapter.out.persistence;

import com.example.adminservice.domain.product.entity.Product;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter @Setter @EqualsAndHashCode(of="id")
@AllArgsConstructor @NoArgsConstructor
@Table(name = "tb_category")
public class CategoryEntity {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @OneToMany(mappedBy = "category")
    private List<ProductEntity> productList;
}
