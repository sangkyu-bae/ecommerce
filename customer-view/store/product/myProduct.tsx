export type MyProduct ={
    id : number
    aggregateIdentifier: string,
    name : string,
    price : number,
    productImage : string,
    description : string
    brand : Data,
    category : Data,
    productComponents: ProductComponent[]
}
export type OrderProduct = {
    productId: number
    color : string
    size : number
    productSizeId : number | null
    quantity : number
    selectPrice : number,
    productName : string
}
export type selectProduct = {
    productId: number
    color : Data
    size : Data
    quantity : number
    selectPrice:number
}

interface ProductComponent{
    color:Data,
    id:number,
    sizes:Size[]
}
interface Size{
    id:number,
    quantity:number,
    size:number
}

interface Data{
    id : number,
    name :string
}
