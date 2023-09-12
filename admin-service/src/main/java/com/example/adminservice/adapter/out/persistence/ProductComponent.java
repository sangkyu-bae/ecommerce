package com.example.adminservice.adapter.out.persistence;

import com.example.adminservice.domain.color.entity.Color;
import com.example.adminservice.domain.size.entity.Size;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Getter @Setter @EqualsAndHashCode(of="id")
@AllArgsConstructor @NoArgsConstructor
@Table(name = "tb_product_component")
public class ProductComponent {

    @Id @GeneratedValue
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Color color;

    @OneToMany
    private Set<Size> sizes;

}
