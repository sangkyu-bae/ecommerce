package com.example.adminservice.adapter.out.persistence.product.entity;

import com.example.adminservice.module.common.error.ErrorException;
import com.example.adminservice.module.common.error.errorImpl.QuantityErrorCode;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter @Setter @EqualsAndHashCode(of="id")
@AllArgsConstructor @NoArgsConstructor
@Table(name = "tb_size")
public class SizeEntity {

    @Id @GeneratedValue
    private long id;

    private int size;

    private int quantity;

    @ManyToOne
    private ProductComponentEntity productComponent;

    public void updateQuantity(int amount){
        if(this.quantity < amount){
            throw new ErrorException(QuantityErrorCode.QUANTITY_BAD_CHANGE,"updateQuantity");
        }else{
            this.quantity = this.quantity - amount;
        }
    }
}
