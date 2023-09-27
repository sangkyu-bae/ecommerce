package com.example.adminservice.adapter.out.persistence.product.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter @Setter @EqualsAndHashCode(of="id")
@AllArgsConstructor @NoArgsConstructor
@Table(name = "tb_color")
public class ColorEntity {

    @Id @GeneratedValue
    private Long id;

    private String name;

}
