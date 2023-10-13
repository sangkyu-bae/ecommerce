package com.example.adminservice.adapter.out.persistence.product.entity;

import com.example.adminservice.module.common.error.ErrorException;
import com.example.adminservice.module.common.error.errorImpl.ProductErrorCode;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter @Setter @EqualsAndHashCode(of="id")
@AllArgsConstructor @NoArgsConstructor
@Table(name = "tb_product") @Builder
public class ProductEntity {

    @Id @GeneratedValue
    private Long id;

    private String name;

    private int price;

    @Lob
    private String description;

    @Lob
    private String productImage;

    @ManyToOne(fetch = FetchType.LAZY)
    private BrandEntity brand;

    @ManyToOne(fetch = FetchType.LAZY)
    private CategoryEntity category;

    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL, mappedBy = "product")
    private Set<ProductComponentEntity> productComponents;

    public void updateProductQuantity(long colorId,int amount,int size){
        ProductComponentEntity productComponent = productComponents.stream()
                .filter(component-> component.getColor().getId() == colorId)
                .findFirst()
                .orElseThrow(()->new ErrorException(ProductErrorCode.PRODUCT_NOT_FOUND,"updateProductQuantity"));

        productComponent.updateQuantity(size,amount);
    }


}
