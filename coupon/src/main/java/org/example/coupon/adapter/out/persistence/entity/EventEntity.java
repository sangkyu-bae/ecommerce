package org.example.coupon.adapter.out.persistence.entity;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
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

@Slf4j
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

    public boolean decreaseQuantity(){
        log.info("quantity : {}",this.quantity);
        if(this.quantity > 0){
            this.quantity -= 1;
            return true;
        }else{
            log.error("quantity : {}",this.quantity);
            log.error("errror");
//            throw new ErrorException(EventErrorCode.EVENT_NOT_ALLOWED,"decreaseQuantity");

            return false;
        }

//        return true;
    }
}
