package com.example.adminservice.adapter.out.persistence;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter @Setter @EqualsAndHashCode(of="id")
@AllArgsConstructor @NoArgsConstructor
@Table(name = "tb_size")
public class SizeEntity {

    @Id @GeneratedValue
    private long id;

    private int size;

    private int quantity;
}
