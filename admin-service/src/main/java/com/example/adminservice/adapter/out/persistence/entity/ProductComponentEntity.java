package com.example.adminservice.adapter.out.persistence.entity;

import com.example.adminservice.infra.error.ErrorException;
import com.example.adminservice.infra.error.ProductErrorCode;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter @Setter @EqualsAndHashCode(of="id")
@AllArgsConstructor @NoArgsConstructor
@Table(name = "tb_product_component")
public class ProductComponentEntity {

    @Id @GeneratedValue
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private ColorEntity color;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL ,mappedBy = "productComponent")
    private Set<SizeEntity> sizes;
    @ManyToOne
    private ProductEntity product;

    public void updateQuantity(int size,int amount){
        SizeEntity sizeEntity = sizes.stream()
                .filter(sizeComponent->sizeComponent.getSize() == size)
                .findFirst()
                .orElseThrow(()->new ErrorException(ProductErrorCode.PRODUCT_NOT_FOUND,"updateQuantity"));

        sizeEntity.updateQuantity(amount);
    }

    public void addSize(SizeEntity size){
        if(sizes == null){
            sizes = new HashSet<>();
        }
        sizes.add(size);
    }

}
