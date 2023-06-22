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
        console.log('타?')
        const {data} = await ApiCommon.loginAPI.get(productRequest.readAllColor);
        console.log("data"+data)
        return data;
    }
}