package com.example.adminservice.domain;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class SizeVo {

    private final Long id;

    private final int size;

    private final int quantity;


    public static SizeVo createGenerateSizeVo(
            SizeId sizeId,
            Size size,
            Quantity quantity
    ){
        return new SizeVo(
                sizeId.getId(),
                size.getSize(),
                quantity.getQuantity()
        );
    }

    @Value
    public static class SizeId{
        public SizeId(long value){
            this.id = value;
        }
        Long id;
    }

    @Value
    public static class Size{
        public Size(int value){
            this.size = value;
        }
        int size;
    }

    @Value
    public static class Quantity{
        public Quantity(int value){
            this.quantity = value;
        }

        int quantity;
    }
}
