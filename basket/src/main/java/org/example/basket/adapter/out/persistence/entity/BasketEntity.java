package org.example.basket.adapter.out.persistence.entity;


import lombok.*;
import org.example.basket.infra.error.BasketErrorCode;
import org.example.basket.infra.error.ErrorException;

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
@Table(name = "tb_basket") @Builder @ToString
public class BasketEntity {

    @Id @GeneratedValue
    private Long id;

    private long productSizeId;

    private long memberId;

    private int productQuantity;

    private int status;

    private long productId;

    private int size;

    private LocalDateTime createAt;

    private LocalDateTime updateAt;

    private String colorName;

    public void updateQuantity(int productQuantity, long memberId){
        if(this.memberId != memberId){
            throw new ErrorException(BasketErrorCode.BASKET_NO_VALIDATE,"updateQuantity");
        }

        this.productQuantity = productQuantity;
    }

}
