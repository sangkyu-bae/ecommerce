package com.example.adminservice.module.domain.quantity.entity;

import com.example.adminservice.module.domain.color.entity.Color;
import com.example.adminservice.module.domain.size.dto.Size;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.Set;

@Entity
@Getter @Setter @EqualsAndHashCode(of="id")
@AllArgsConstructor @NoArgsConstructor
public class Quantity {

    @Id @GeneratedValue
    private Long id;

    private int quantity;

}
