package com.example.delivery.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;

import java.time.LocalDate;
import java.util.Arrays;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class DeliveryVo {

    private final Long id;

    private Long sizeId;

    private Long userId;

    private Long orderId;

    private String address;

    private Integer status;

    private LocalDate createAt;

    private LocalDate updateAt;

    public static DeliveryVo createGenerateDeliveryVo(
            DeliveryId deliveryId,
            DeliverySizeId deliverySizeId,
            DeliveryUserId deliveryUserId,
            DeliveryOrderId deliveryOrderId,
            DeliveryAddress deliveryAddress,
            DeliveryStatus deliveryStatus,
            DeliveryCreateAt deliveryCreateAt,
            DeliveryUpdateAt deliveryUpdateAt

    ){

        return new DeliveryVo(
                deliveryId.getId(),
                deliverySizeId.getId(),
                deliveryUserId.getUserId(),
                deliveryOrderId.getOrderId(),
                deliveryAddress.getAddress(),
                deliveryStatus.getStatus(),
                deliveryCreateAt.getCreateAt(),
                deliveryUpdateAt.getUpdateAt()
        );
    }

    @Value
    public static class DeliveryStatus{
        public DeliveryStatus(Integer value){
            this.status = value;
        }
        private Integer status;
    }

    @Value
    public static class DeliveryId{

        public DeliveryId(long value){
            this.id = value;
        }
        private Long id;
    }

    @Value
    public static class DeliverySizeId{

        public DeliverySizeId(Long value){
            this.id = value;
        }
        private Long id;
    }

    @Value
    public static class DeliveryUserId{

        public DeliveryUserId(Long value){
            this.userId = value;
        }
        private Long userId;
    }

    @Value
    public static class DeliveryOrderId{

        public DeliveryOrderId(Long value){
            this.orderId = value;
        }
        private Long orderId;
    }

    @Value
    public static class DeliveryAddress{

        public DeliveryAddress(String value){
            this.address = value;
        }
        private String address;
    }

    @Value
    public static class DeliveryCreateAt{

        public DeliveryCreateAt(LocalDate value){
            this.createAt = value;
        }
        private LocalDate createAt;
    }

    @Value
    public static class DeliveryUpdateAt{
        public DeliveryUpdateAt(LocalDate value){
            this.updateAt = value;
        }

        private LocalDate updateAt;
    }

    public static enum StatusCode{

        READY(1,"배송대기");
        private final int status;
        private final String name;
        StatusCode(int status,String name){
            this.status = status;
            this.name = name;
        }

        public int getStatus() {
            return status;
        }

        public static StatusCode findStatusCode(int status){
            return Arrays.stream(StatusCode.values())
                    .filter(couponStatus -> couponStatus.getStatus()== status)
                    .findFirst()
                    .orElseThrow();
        }
    }

}
