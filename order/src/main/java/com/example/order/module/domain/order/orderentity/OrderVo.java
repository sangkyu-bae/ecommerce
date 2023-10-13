package com.example.order.module.domain.order.orderentity;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;

import java.time.LocalDate;

@AllArgsConstructor(access =  AccessLevel.PRIVATE)
@Getter
public class OrderVo {

    private final long id;

    private final long productId;

    private final long colorId;

    private final long sizeId;

    private final int amount;

    private final int payment;

    private final String address;

    private final LocalDate createAt;

    private final LocalDate updateAt;

    private int status;

    public static OrderVo createGenerateOrderVo(
            OrderId orderId,
            OrderProductId orderProductId,
            OrderColorId orderColorId,
            OrderSizeId orderSizeId,
            OrderAmount orderAmount,
            OrderPayment orderPayment,
            OrderAddress orderAddress,
            OrderCreateAt orderCreateAt,
            OrderUpdateAt orderUpdateAt,
            OrderStatus orderStatus
    ){
        return new OrderVo(
                orderId.getId(),
                orderProductId.getProductId(),
                orderColorId.getColorId(),
                orderSizeId.getSizeId(),
                orderAmount.getAmount(),
                orderPayment.getPayment(),
                orderAddress.getAddress(),
                orderCreateAt.getCreateAt(),
                orderUpdateAt.getUpdateAt(),
                orderStatus.getStatus()
        );
    }

    @Value
    public static class OrderId{

        public OrderId(long value){
            this.id = value;
        }
        private long id;
    }

    @Value
    public static class OrderProductId{
        public OrderProductId(long value){
            this.productId = value;
        }
        private long productId;
    }

    @Value
    public static class OrderColorId{

        public OrderColorId(long value){
            this.colorId = value;
        }
        private long colorId;
    }

    @Value
    public static class OrderSizeId{
        public OrderSizeId(long value){
            this.sizeId = value;
        }
        private long sizeId;
    }

    @Value
    public static class OrderPayment{
        public OrderPayment(int value){
            this.payment = value;
        }
        private int payment;
    }

    @Value
    public static class OrderAddress{
        public OrderAddress(String value){
            this.address = value;
        }
        private String address;
    }

    @Value
    public static class OrderCreateAt{
        public OrderCreateAt(LocalDate value){
            this.createAt = value;
        }
        private LocalDate createAt;
    }

    @Value
    public static class OrderUpdateAt{

        public OrderUpdateAt(LocalDate value){
            this.updateAt = value;
        }
        private LocalDate updateAt;
    }

    @Value
    public static class OrderAmount{
        public OrderAmount(int value){
            this.amount = value;
        }
        int amount;
    }

    @Value
    public static class OrderStatus{
        public OrderStatus(int value){
            this.status = value;
        }
        int status;
    }

    public static enum StatusCode{
        ORDER(1,"주문완료"),

        PAYMENT(2,"결제완료"),

        DELIVERY_READY(3,"배송대기"),

        DELIVERY_ING(4,"배송중"),

        DELIVERY_OK(5,"배송완료"),

        PAY_OK(6,"구매완료");

        private final int status;
        private final String name;
        StatusCode(int status, String name){
            this.status = status;
            this.name = name;
        }

        public int getStatus(){
            return status;
        }
    }


}