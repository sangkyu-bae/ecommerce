package com.example.adminservice.domain.size.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

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
