package org.example.basket.domain;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Arrays;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter @ToString
public class Basket {

    private final Long id;

    private final long productSizeId;

    private final long memberId;

    private final int productQuantity;

    private final int status;

    private long productId;

    private int size;

    private String colorName;

    private final LocalDateTime creatAt;

    private final LocalDateTime updateAt;


    public static Basket createGenerateBasket(
            BasketId basketId,
            BasketMemberId basketMemberId,
            BasketProductSizeId productSizeId,
            BasketProductQuantity basketProductQuantity,
            BasketStatus basketStatus,
            BasketProductId basketProductId,
            BasketSize basketSize,
            BasketColorName basketColorName,
            BasketCreateAt basketCreateAt,
            BasketUpdateAt basketUpdateAt
    ){

        return new Basket(
                basketId.getId(),
                productSizeId.getProductSizeId(),
                basketMemberId.getMemberId(),
                basketProductQuantity.getQuantity(),
                basketStatus.getStatus(),
                basketProductId.getProductId(),
                basketSize.getSize(),
                basketColorName.getColorName(),
                basketCreateAt.getCreateAt(),
                basketUpdateAt.getUpdateAt()
        );
    }

    @Value
    public static class BasketId{

        public BasketId(Long value){
            this.id = value;
        }

        private Long id;
    }

    @Value
    public static class BasketProductSizeId{
        public BasketProductSizeId(long value){
            this.productSizeId = value;
        }

        private long productSizeId;
    }

    @Value
    public static class BasketMemberId{

        public BasketMemberId(long value){
            this.memberId = value;
        }

        private long memberId;
    }

    @Value
    public static class BasketProductQuantity{

        public BasketProductQuantity(int value){
            this.quantity = value;
        }
        private int quantity;
    }

    public static enum BasketStatus{
        CREATE(1,"장바구니 생성"),
        DELETE(2,"장바구니 삭제"),
        ORDER_DELETE(3,"주문 완료 장바구니 삭제");

        private final int status;

        private final String name;

        BasketStatus(int status, String name){
            this.name = name;
            this.status =status;
        }

        public int getStatus(){
            return this.status;
        }

        public String getName(){
            return this.name;
        }

        public static BasketStatus findStatus(int status){
            return Arrays.stream(BasketStatus.values())
                    .filter(statusCode -> statusCode.getStatus()== status)
                    .findFirst()
                    .orElseThrow();
        }
    }

    @Value
    public static class BasketProductId{
        private long productId;

        public BasketProductId(long value){
            this.productId = value;
        }
    }

    @Value
    public static class BasketSize{
        private int size;

        public BasketSize(int value){
            this.size = value;
        }
    }

    @Value
    public static class BasketColorName{
        private String colorName;

        public BasketColorName(String val){
            this.colorName = val;
        }
    }
    @Value
    public static class BasketCreateAt{

        public BasketCreateAt(LocalDateTime value){
            this.createAt = value;
        }

        private LocalDateTime createAt;
    }

    @Value
    public static class BasketUpdateAt{

        public BasketUpdateAt(LocalDateTime value){
            this.updateAt = value;
        }

        private LocalDateTime updateAt;
    }

}
