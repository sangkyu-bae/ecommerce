package com.example.adminservice.adapter.out.persistence.product;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter @Setter @EqualsAndHashCode(of="id")
@AllArgsConstructor @NoArgsConstructor
@Table(name = "tb_size")
public class SizeEntity {

    @Id @GeneratedValue
    private long id;

    private int size;

    private int quantity;

    @ManyToOne
    private ProductComponentEntity productComponent;
}
