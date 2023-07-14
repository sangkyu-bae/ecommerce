package com.example.adminservice.module.domain.quantity.entity;

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

}
