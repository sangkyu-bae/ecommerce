package com.example.adminservice.module.domain.product.entity;

import com.example.adminservice.module.domain.color.entity.Color;
import com.example.adminservice.module.domain.size.entity.Size;
import com.example.adminservice.module.domain.size.entity.SizeQuantity;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter @EqualsAndHashCode(of="id")
@AllArgsConstructor @NoArgsConstructor @Builder
public class ColorProduct {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "color_id")
    private Color color;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="product_id")
    private Product product;

    @OneToMany(mappedBy = "colorProduct", cascade = CascadeType.ALL)
    private List<SizeQuantity> sizeList;

    public ColorProduct(Color color,Product product){
        this.color = color;
        this.product = product;
    }

    private void addSize(SizeQuantity sizeQuantity){
        if(sizeList ==null){
            sizeList =new ArrayList<>();
        }
        if(!sizeList.contains(sizeQuantity)){
            this.sizeList.add(sizeQuantity);
        }
    }

    public void addSizeAll(List<SizeQuantity> sizeQuantityList){
        sizeQuantityList.stream().forEach(sizeQuantity -> addSize(sizeQuantity));
    }
}
