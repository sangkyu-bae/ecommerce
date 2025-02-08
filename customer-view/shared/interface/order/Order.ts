interface Order{
    products : RegisterOrder[]
    address : string | any
    phone : string | any
}

interface RegisterOrder{
    productId : number,
    colorId : number,
    sizeId : number | null,
    amount : number,
    payment : number,
    couponId : number | null,
    productName: string
}