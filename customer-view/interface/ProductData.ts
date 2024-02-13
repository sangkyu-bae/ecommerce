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
    productList : Product[],
}

interface Product{
    id : number
    name : string,
    price : number,
    productImage : string,
    description : string
    brand : brandData,
    category : Data,
    colorDataList : colorProductData[]
    // colorDataList : ColorData[]
}

interface RankProduct{
    id:number,
    productName: string
}

interface brandData extends Data{
    brandImage : string,
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