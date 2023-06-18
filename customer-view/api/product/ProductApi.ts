import ApiCommon from "@/api/common/ApiCommon";
import {productRequest} from "@/api/common/Url";

export const ProductApi = {
    createProduct: async (product:ProductData)=>{
        const {data} = await ApiCommon.loginAPI.post(productRequest.createProduct,product);
        return data;
    }
}