interface Order{
    productId : number,
    colorId : number,
    sizeId : number,
    amount : number,
    payment: number,
    address : string,
    couponId : number | null;
}