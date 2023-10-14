package com.example.order.application.port.in.command;

import com.example.order.common.SelfValidating;
import lombok.*;


@Builder @Data
@AllArgsConstructor @NoArgsConstructor
public class RegisterOrderCommand extends SelfValidating<RegisterOrderCommand> {

    private long productId;

    private long colorId;

    private long sizeId;

    private int amount;

    private int payment;

    private String address;

    private int status;

    private String userId;

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
