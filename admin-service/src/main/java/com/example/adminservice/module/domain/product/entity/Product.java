package com.example.adminservice.module.domain.product.entity;

import com.example.adminservice.module.domain.brand.entity.Brand;
import com.example.adminservice.module.domain.category.entity.Category;
import com.example.adminservice.module.domain.quantity.entity.Quantity;
import com.example.adminservice.module.domain.size.entity.SizeQuantity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

@Entity
@Getter @Setter @EqualsAndHashCode(of="id")
@AllArgsConstructor @NoArgsConstructor
public class Product {

    @Id @GeneratedValue
    private Long id;

    private String name;

    private int price;

    @Lob
    private String description;

    private LocalDate createAt;

    private LocalDate updateAt;

    @Lob
    private String productImage;

    @ManyToOne(fetch = FetchType.LAZY)
    private Brand brand;
    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<ColorProduct> colorProductList = new ArrayList<>();

    private void addColorProduct(ColorProduct colorProduct){
        if(!colorProductList.contains(colorProduct)){
            this.colorProductList.add(colorProduct);
        }
    }

    public void addColorProductAll(List<ColorProduct> colorProductList){
        for(ColorProduct colorProduct:colorProductList){
            boolean isColor = this.checkColor(colorProduct);
            if(!isColor) addColorProduct(colorProduct);
        }
//        colorProductList.stream().forEach(colorProduct -> addColorProduct(colorProduct));
    }

    public boolean checkColor(ColorProduct colorProduct){
        Map<Long,SizeQuantity> quantityMap =new HashMap<>();
        boolean isColor = false;
        for(ColorProduct nowProduct:this.colorProductList){
            quantityMap.isEmpty();
            Long nowProductColorId = nowProduct.getColor().getId();
            Long updateProductColorId = colorProduct.getColor().getId();

            if(nowProductColorId==updateProductColorId){
                isColor =true;
                for(SizeQuantity sizeQuantity:nowProduct.getSizeList()){
                    quantityMap.put(sizeQuantity.getSize().getId(),sizeQuantity);
                }

                for(SizeQuantity sizeQuantity :colorProduct.getSizeList()){
                    Long sizeId =sizeQuantity.getSize().getId();
                    int quantity = sizeQuantity.getQuantity().getQuantity();

                    if(quantityMap.containsKey(sizeId)){
                        SizeQuantity updateSizeQuantity = quantityMap.get(sizeId);
                        Quantity updateQuantity = updateSizeQuantity.getQuantity();
                        updateQuantity.setQuantity(quantity);
                    }else{
                        nowProduct.addSize(sizeQuantity);
                    }
                }
            }
        }
        
        return isColor;
    }
}
