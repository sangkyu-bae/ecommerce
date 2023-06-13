package com.example.adminservice.module.domain.size.entity;

import com.example.adminservice.module.domain.quantity.entity.Quantity;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Getter @Setter @EqualsAndHashCode(of="id")
@AllArgsConstructor @NoArgsConstructor
public class Size {

    @Id @GeneratedValue
    private Long id;

    private int size;

    @OneToMany(mappedBy = "size")
    private List<SizeQuantity> sizeQuantityList;
}
