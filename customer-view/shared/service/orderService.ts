import {OrderProduct} from "@/store/product/myProduct";

export class OrderService{
    constructor() {

    }

    mapToOrder(orderProduct : OrderProduct) : RegisterOrder{
        return {
            productId : orderProduct.productId,
            colorId : 1,
            sizeId : orderProduct.productSizeId,
            amount : orderProduct.quantity,
            payment : orderProduct.selectPrice,
            couponId : null,
            productName : orderProduct.productName
        };
    }
}