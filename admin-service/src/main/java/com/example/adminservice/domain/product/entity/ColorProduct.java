package com.example.adminservice.domain.product.entity;

import com.example.adminservice.adapter.out.persistence.Product;
import com.example.adminservice.domain.color.entity.Color;
import com.example.adminservice.domain.quantity.entity.Quantity;
import com.example.adminservice.domain.size.entity.SizeQuantity;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter @Setter @EqualsAndHashCode(of="id")
@AllArgsConstructor @NoArgsConstructor @Builder
public class ColorProduct {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "color_id")
    private Color color;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name="product_id")
    private Product product;

    @OneToMany(mappedBy = "colorProduct", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<SizeQuantity> sizeList=new HashSet<>();

    public ColorProduct(Color color, Product product){
        this.color = color;
        this.product = product;
    }

    public void removeSize(SizeQuantity sizeQuantity){
        if(sizeList.contains(sizeQuantity)){
            sizeList.remove(sizeQuantity);
        }
    }
    public void addSize(SizeQuantity sizeQuantity){
        if(sizeList == null){
            sizeList = new HashSet<>();
        }
        this.sizeList.add(sizeQuantity);
    }

    public void addSizeAll(Set<SizeQuantity> sizeQuantityList){
        sizeQuantityList.stream().forEach(sizeQuantity -> addSize(sizeQuantity));
    }

    public Quantity readMinQuantity(Long sizeId){
        SizeQuantity sameSize = sizeList.stream().filter(sizeQuantity -> sizeQuantity.getSizeId() == sizeId)
                .findFirst().orElseThrow(()->new IllegalArgumentException());
        Quantity quantity = sameSize.getQuantity();

        return quantity;
    }

}
