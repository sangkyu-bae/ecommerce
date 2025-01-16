package com.example.order.domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

@AllArgsConstructor(access =  AccessLevel.PRIVATE)
@Getter
public class OrderVo {

    private final long id;

    private final long userId;

    private final int payment;

    private final String address;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime createAt;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime updateAt;

    private final int status;

    private final TypeEnumMapper statusCode;

    private final String aggregateIdentifier;


    public static OrderVo createGenerateOrderVo(
            OrderId orderId,
            OrderProductUserId orderProductUserId,
            OrderPayment orderPayment,
            OrderAddress orderAddress,
            OrderCreateAt orderCreateAt,
            OrderUpdateAt orderUpdateAt,
            OrderStatus orderStatus,
            StatusCode statusCode,
            OrderAggregateIdentifier orderAggregateIdentifier
    ){
        return new OrderVo(
                orderId.getId(),
                orderProductUserId.getUserId(),
                orderPayment.getPayment(),
                orderAddress.getAddress(),
                orderCreateAt.getCreateAt(),
                orderUpdateAt.getUpdateAt(),
                orderStatus.getStatus(),
                new TypeEnumMapper(statusCode),
                orderAggregateIdentifier.getAggregateIdentifier()
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
    public static class OrderProductUserId{

        public OrderProductUserId(long value){
            this.userId = value;
        }
        private long userId;
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
        public OrderCreateAt(LocalDateTime value){
            this.createAt = value;
        }
        private LocalDateTime createAt;
    }

    @Value
    public static class OrderUpdateAt{

        public OrderUpdateAt(LocalDateTime value){
            this.updateAt = value;
        }
        private LocalDateTime updateAt;
    }


    @Value
    public static class OrderStatus{
        public OrderStatus(int value){
            this.status = value;
        }
        int status;
    }

    @Value
    public static class OrderAggregateIdentifier{
        public OrderAggregateIdentifier(String value) {
            this.aggregateIdentifier = value;
        }

        String aggregateIdentifier;
    }


    public static enum StatusCode implements EnumMapperType{
        ORDER(1,"주문완료"),

        PAYMENT(2,"결제완료"),

        DELIVERY_READY(3,"배송대기"),

        DELIVERY_ING(4,"배송중"),

        DELIVERY_OK(5,"배송완료"),

        PAY_OK(6,"구매완료"),

        ORDER_REMOVE_READY(7,"주문취소대기"),

        ORDER_REMOVE_SUCCESS(8,"주문취소완료"),

        ORDER_REMOVE_FAIL(9,"주문취소실패");

        private final int status;
        private final String name;
        StatusCode(int status, String name){
            this.status = status;
            this.name = name;
        }

        @Override
        public int getStatus(){
            return status;
        }

        @Override
        public String getName() {
            return name;
        }

        public static StatusCode findStatusCode(int status){
            return Arrays.stream(StatusCode.values())
                    .filter(statusCode -> statusCode.getStatus()== status)
                    .findFirst()
                    .orElseThrow();
        }
    }
}
