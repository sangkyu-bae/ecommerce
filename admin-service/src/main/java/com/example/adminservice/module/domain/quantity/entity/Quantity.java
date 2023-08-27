package com.example.adminservice.module.domain.quantity.entity;

import com.example.adminservice.module.common.error.ErrorException;
import com.example.adminservice.module.common.error.errorImpl.BrandErrorCode;
import com.example.adminservice.module.common.error.errorImpl.QuantityErrorCode;
import com.example.adminservice.module.domain.size.entity.SizeQuantity;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter @Setter @EqualsAndHashCode(of="id")
@AllArgsConstructor @NoArgsConstructor @Builder
public class Quantity {

    @Id @GeneratedValue
    private Long id;

    private int quantity;

    @OneToMany(mappedBy = "quantity",cascade = CascadeType.ALL)
    private List<SizeQuantity> sizeQuantityList;

    public void changeQuantity(int minQuantity){
        if(quantity > 0 && quantity-minQuantity > 0){
            this.quantity -= minQuantity;
        }else{
            throw new ErrorException(QuantityErrorCode.QUANTITY_BAD_CHANGE,"changeQuantity");
        }
    }
    public void adminUpdateQuantity(int quantity){

    }
}
