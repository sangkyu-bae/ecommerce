import ApiCommon from "@/api/common/ApiCommon";
import {productRequest} from "@/api/common/Url";

export const ProductApi = {
    createProduct: async (product:ProductData)=>{
        const {data} = await ApiCommon.loginAPI.post(productRequest.createProduct,product);
        return data;
    },
    readAllBrand: async ():Promise<any>=>{
        const {data} = await ApiCommon.loginAPI.get(productRequest.readAllBrand);
        return data;
    },
    readAllCategory : async ()=>{
        const {data} = await ApiCommon.loginAPI.get(productRequest.readAllCategory);
        return data;
    },
    readAllColor : async ()=>{
        const {data} = await ApiCommon.loginAPI.get(productRequest.readAllColor);
        return data;
    },
    readAllSize : async ()=>{
        const {data} = await ApiCommon.loginAPI.get(productRequest.readAllSize);
        return data;
    }
}