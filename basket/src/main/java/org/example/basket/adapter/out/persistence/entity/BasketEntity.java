package org.example.basket.adapter.out.persistence.entity;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of="id")
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_basket") @Builder
public class BasketEntity {

    @Id @GeneratedValue
    private Long id;

    private long productSizeId;

    private long memberId;

    private int productQuantity;

    private int status;

    private LocalDateTime createAt;

    private LocalDateTime updateAt;


}
