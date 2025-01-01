package com.example.adminservice.adapter.out.persistence.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter @Setter @EqualsAndHashCode(of="id")
@AllArgsConstructor @NoArgsConstructor
@Table(name = "tb_color") @ToString @Builder
public class ColorEntity {

    @Id @GeneratedValue
    private Long id;

    private String name;

}
