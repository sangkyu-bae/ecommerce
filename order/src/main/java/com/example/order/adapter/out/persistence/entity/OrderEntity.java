package com.example.order.adapter.out.persistence.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

//주문원장
@Entity
@Getter @Setter @EqualsAndHashCode(of="id")
@AllArgsConstructor @NoArgsConstructor
@Table(name = "tb_order") @Builder
public class OrderEntity {

    @Id @GeneratedValue
    private Long id;

    private long userId;

    private int payment;

    private String address;

    private String phoneNumber;

    private LocalDateTime createAt;

    private LocalDateTime updateAt;

    private int status;

    private String aggregateIdentifier;

    @OneToMany
    private List<ProductEntity> productList;

    public void addProduct(ProductEntity productEntity){
        if(productList == null){
            productList = new ArrayList<>();
        }

        productList.add(productEntity);
    }

    public Set<Long> getProductIds(){
        if(productList == null){
            productList = new ArrayList<>();
        }

        Set<Long> ids = this.productList.stream()
                .map(ProductEntity::getProductId)
                .collect(Collectors.toSet());

        return ids;
    }

}
