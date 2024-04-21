interface ProductData{
    name : string,
    price : number,
    description : string,
    category : number,
    brand : number,
    image :string,
    colorDataList : ColorData[]
}


interface ProductPageData{
    pageNumber : number,
    pageSize : number,
    totalElements : number,
    productVoList : Product[],
}

interface Product{
    id : number
    // aggregateIdentifier: string,
    name : string,
    price : number,
    productImage : string,
    description : string
    brand : Data,
    category : Data,
    productComponents: ProductComponent[]
}

interface RankProduct{
    productId:number,
    productName: string
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

interface colorProductData{
    colorDto : Data,
    sizeQuantityDtoList : sizeQuantityData[]
}

interface sizeQuantityData{
    quantity : number
    sizeDto : sizeData
}

interface Data{
    id : number,
    name :string
}

interface sizeData{
    id : number,
    size: number
}