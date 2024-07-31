package org.example.coupon.adapter.out.persistence.entity;

import lombok.*;
import org.example.coupon.infra.error.ErrorException;
import org.example.coupon.infra.error.EventErrorCode;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of="id")
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_event") @Builder
public class EventEntity {

    @Id @GeneratedValue
    private Long id;

    private String couponName;

    private int salePercent;
    private int quantity;

    private LocalDateTime startAt;

    private LocalDateTime endAt;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "event")
    private List<CouponComponentEntity> couponComponents;

    public void decreaseQuantity(){
        if(quantity > 0){
            this.quantity -= 1;
        }else{
            throw new ErrorException(EventErrorCode.EVENT_NOT_ALLOWED,"decreaseQuantity");
        }
    }
}
