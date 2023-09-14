package com.example.adminservice.domain.product.entity;

import com.example.adminservice.domain.brand.entity.Brand;
import com.example.adminservice.domain.category.entity.Category;
import com.example.adminservice.domain.product.OrderDto;
import com.example.adminservice.domain.product.entity.ColorProduct;
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
        resetColorProduct();
        colorProductList.stream().forEach(colorProduct -> addColorProduct(colorProduct));
    }

    private void resetColorProduct(){
        if(!this.colorProductList.isEmpty()){
            this.colorProductList.clear();
        }
    }

    public ColorProduct colorProduct(OrderDto orderDto){
        ColorProduct readColorProduct = colorProductList.stream().
                filter(colorProduct -> colorProduct.getColor().getId() == orderDto.getColorId()).
                findFirst().orElseThrow(()->new IllegalArgumentException());

        return readColorProduct;
    }

}
