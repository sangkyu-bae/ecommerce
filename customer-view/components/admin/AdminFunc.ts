import Util from "@/utils/CommonUtil";

class AdminFunc{
    constructor() {
    }
    toColorData= (colorProductData:colorProductData) : ColorData=>{
        let sizeQuantityDataList :SizeQuantityData[];
        const colorName :string =colorProductData.colorDto.name;
        sizeQuantityDataList = colorProductData.sizeQuantityDtoList
            .map(sizeQuantity=>{
                let size :Size= sizeQuantity.sizeDto;
                let quntity :number=sizeQuantity.quantity;
                const parseSizeQuntitiy : SizeQuantityData = {
                    size:size,
                    quantity:quntity
                }

                return parseSizeQuntitiy
            })

        const colorData : ColorData={
            colorName:colorName,
            colorSize : sizeQuantityDataList
        }
        return colorData
    }
    toProductData = (productData :ProductData):Product => {
        let product: Product = { // 변수 초기화
            id: 0, // 예시로 0으로 초기화, 필요에 따라 수정해주세요
            name: '',
            price: 0,
            productImage: '',
            description: '',
            brand: {
                id: 0,
                brandImage:'',
                name:''
            }, // 예시로 0으로 초기화, 필요에 따라 수정해주세요
            category: {
                id: 0 ,
                name:''
            }, // 예시로 0으로 초기화, 필요에 따라 수정해주세요
            colorDataList: [],
        };
        product.name ='sdkjskj';
        product.price = productData.price;
        product.productImage = productData.image;
        product.description = productData.description;
        product.brand.id = productData.brand;
        product.category.id = productData.category;
        product.colorDataList =productData.colorDataList.map(data=>{
            return this.toColorProductData(data)
        });
        return product
    }
    toColorProductData =(colorData:ColorData):colorProductData=>{
        const sizeQuantity :sizeQuantityData[] = this.toSizeQuantity(colorData.colorSize);
        let colorProduct :colorProductData= {
            colorDto:{
                name:'',
                id : colorData.colorName !=NaN ? parseInt(colorData.colorName):0
            },
            sizeQuantityDtoList:sizeQuantity
        }
        return colorProduct
    }
    toSizeQuantity=(colorSize : SizeQuantityData[]):sizeQuantityData[]=>{
        let colorProductData : sizeQuantityData[]=colorSize.map(data=>{
            let colorData : sizeQuantityData={
                quantity:data.quantity,
                sizeDto:{
                    size:data.size.size,
                    id:data.size.id
                }
            };
            return colorData;
        })
        return colorProductData
    }
}
export default AdminFunc;