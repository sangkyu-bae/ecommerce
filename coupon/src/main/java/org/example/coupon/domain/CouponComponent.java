package org.example.coupon.domain;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.Arrays;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class CouponComponent {

    private final Long id;

    private final long userId;

    private final int status;

    private final LocalDateTime endAt;

    private final Coupon coupon;


    public static CouponComponent createGenerateCouponComponentVo(
            CouponComponentId couponComponentId,
            CouponComponentUserId couponComponentUserId,
            CouponStatusCode statusCode,
            CouponComponentEndAt couponComponentEndAt,
            Coupon couponVo
    ){
        return new CouponComponent(
                couponComponentId.getId(),
                couponComponentUserId.getUserId(),
                statusCode.getStatus(),
                couponComponentEndAt.getEndAt(),
                couponVo
        );
    }

    @Value
    public static class CouponComponentId{
        Long id;

        public CouponComponentId(Long value){
            this.id = value;
        }
    }

    @Value
    public static class CouponComponentUserId{
        long userId;

        public CouponComponentUserId(long value){
            this.userId = value;
        }
    }

    @Value
    public static class CouponComponentEndAt{
        LocalDateTime endAt;

        public CouponComponentEndAt(LocalDateTime value) {
            this.endAt = value;
        }
    }

    public static enum CouponStatusCode {

        PUBLISH(0,"발급"),

        USE_READY(1,"사용대기"),
        USE_SUCCESS(2,"사용완료"),

        USE_FAIL(3,"사용취소");

        private final int status;

        private final String name;

        CouponStatusCode(int status, String name){
            this.status= status;
            this.name = name;
        }

        public int getStatus(){
            return this.status;
        }

        public String getName(){
            return this.name;
        }

        public static CouponStatusCode findStatusCode(int status){
            return Arrays.stream(CouponStatusCode.values())
                    .filter(couponStatus -> couponStatus.getStatus()== status)
                    .findFirst()
                    .orElseThrow();
        }
    }
}
