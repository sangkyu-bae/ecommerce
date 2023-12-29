package org.example.coupon.domain;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class CouponVo {

    private final Long id;

    private final Long createAdminId;

    private final int salePercent;

    private final String name;

    private final LocalDateTime creatAt;
    @Setter
    private List<CouponComponentVo> couponComponentVoList;

    private String aggregateIdentifier;
    public static CouponVo createGenerateCouponVo(
            CouponId couponId,
            CouponCreateAdminId couponCreateAdminUserId,
            CouponSalePercent couponSalePercent,
            CouponName couponName,
            CouponCreateAt couponCreateAt,
            List<CouponComponentVo> couponComponentVoList,
            CouponAggregateIdentifier couponAggregateIdentifier
    ){
        return new CouponVo(
                couponId.getId(),
                couponCreateAdminUserId.getCreateAdminId(),
                couponSalePercent.getSalePercent(),
                couponName.getName(),
                couponCreateAt.getCreateAt(),
                couponComponentVoList,
                couponAggregateIdentifier.getAggregateIdentifier()
        );
    }

    @Value
    public static class CouponId{
        Long id;

        public CouponId(Long value){
            id = value;
        }
    }

    @Value
    public static class CouponCreateAdminId {
        Long createAdminId;

        public CouponCreateAdminId(Long value){
            this.createAdminId = value;
        }
    }

    @Value
    public static class CouponSalePercent {
        int salePercent;

        public CouponSalePercent(int value){
            this.salePercent = value;
        }
    }

    @Value
    public static class CouponName{
        String name;

        public CouponName(String value){
            this.name = value;
        }
    }

    @Value
    public static class CouponCreateAt{
        LocalDateTime createAt;

        public CouponCreateAt(LocalDateTime value){
            this.createAt = value;
        }
    }


    @Value
    public static class CouponStatus{
        int status;

        public CouponStatus(int value){
            this.status = value;
        }
    }

    @Value
    public static class CouponAggregateIdentifier{
        String aggregateIdentifier;

        public CouponAggregateIdentifier(String value){
            this.aggregateIdentifier = value;
        }
    }

}
