package com.example.adminservice.adapter.out.persistence.entity;

import com.example.adminservice.infra.error.ErrorException;
import com.example.adminservice.infra.error.QuantityErrorCode;
import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Table(name = "tb_size")
public class SizeEntity {

    @Id @GeneratedValue
    private Long id;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SizeEntity sizeEntity = (SizeEntity) o;
        return Objects.equals(id, sizeEntity.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : super.hashCode();
    }
}
