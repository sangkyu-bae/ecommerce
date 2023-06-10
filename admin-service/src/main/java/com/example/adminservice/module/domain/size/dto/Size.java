package com.example.adminservice.module.domain.size.dto;

import com.example.adminservice.module.domain.quantity.entity.Quantity;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter @Setter @EqualsAndHashCode(of="id")
@AllArgsConstructor @NoArgsConstructor
public class Size {

    @Id @GeneratedValue
    private Long id;

    private int size;

    @OneToMany
    private Set<Quantity> quantity;
}
