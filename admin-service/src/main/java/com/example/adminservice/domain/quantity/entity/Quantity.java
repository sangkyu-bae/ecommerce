package com.example.adminservice.domain.quantity.entity;

import com.example.adminservice.module.common.error.ErrorException;
import com.example.adminservice.module.common.error.errorImpl.QuantityErrorCode;
import com.example.adminservice.domain.size.entity.SizeQuantity;
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

    public boolean changeQuantity(int minQuantity){
        if(quantity > 0 && quantity-minQuantity > 0){
            this.quantity -= minQuantity;
            return true;
        }else{
            throw new ErrorException(QuantityErrorCode.QUANTITY_BAD_CHANGE,"changeQuantity");
        }
    }
}