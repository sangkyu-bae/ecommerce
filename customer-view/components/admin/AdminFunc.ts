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
}
export default AdminFunc;