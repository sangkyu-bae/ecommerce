package com.example.adminservice.module.domain.quantity.entity;

import com.example.adminservice.module.domain.size.entity.SizeQuantity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Getter @Setter @EqualsAndHashCode(of="id")
@AllArgsConstructor @NoArgsConstructor @Builder
public class Quantity {

    @Id @GeneratedValue
    private Long id;

    private int quantity;

    @OneToMany(mappedBy = "quantity")
    private List<SizeQuantity> sizeQuantityList;

}
