// interface Order{
//     productId : number,
//     colorId : number,
//     sizeId : number,
//     amount : number,
//     payment: number,
//     address : string,
//     couponId : number | null;
// }

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