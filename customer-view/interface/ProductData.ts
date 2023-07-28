interface ProductData{
    name : string,
    price : number,
    description : string,
    category : string,
    brand : string,
    image :string,
    colorDataList : ColorData[]
}


interface ProductPageData{
    productList : Product[],
    brandDto : brandData,
    categoryDto : Data,
    colorProductDtoList : colorProductData[]
}

interface Product{
    name : string,
    price : number,
    productImage : string,
    description : string
}

interface brandData extends Data{
    brandImage : string,
}

interface Data{
    id : number,
    name :string
}

interface colorProductData{
    colorDto : Data,
    sizeQuantityDtoList : sizeQuantityData[]
}

interface sizeQuantityData{
    quantity : number,
    sizeData : sizeData
}
interface sizeData{
    id : number,
    size: number
}