package com.example.adminservice.module.domain.size.entity;

import com.example.adminservice.module.domain.product.entity.ColorProduct;
import com.example.adminservice.module.domain.quantity.entity.Quantity;
import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter @Setter @EqualsAndHashCode(of="id")
@AllArgsConstructor @NoArgsConstructor @Builder
public class SizeQuantity {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "size_id")
    private Size size;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quantity_id")
    private Quantity quantity;

    @ManyToOne
    private ColorProduct colorProduct;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SizeQuantity that = (SizeQuantity) o;

        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public long getSizeId(){
        return size.getId();
    }
}
