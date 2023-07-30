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
    pageNumber : number,
    pageSize : number,
    totalElements : number,
    productList : Product[],
}

interface Product{
    name : string,
    price : number,
    productImage : string,
    description : string
    brandDto : brandData,
    categoryDto : Data,
    colorProductDtoList : colorProductData[]
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