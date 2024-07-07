package com.example.adminservice.adapter.out.persistence.entity;

import com.example.adminservice.infra.error.ErrorException;
import com.example.adminservice.infra.error.ProductErrorCode;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor @Builder @ToString
@Table(name = "tb_product_component")
public class ProductComponentEntity {

    @Id @GeneratedValue
    private Long id;

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
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductComponentEntity productComponent = (ProductComponentEntity) o;
        return Objects.equals(id, productComponent.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : super.hashCode();
    }

}
