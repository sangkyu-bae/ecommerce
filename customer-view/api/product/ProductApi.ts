import ApiCommon from "@/api/common/ApiCommon";
import {productRequest, productURL} from "@/constants/Url";

export const ProductApi = {
    createProduct: async (product:ProductData)=>{
        const {data} = await ApiCommon.loginJsonAPI.post(productRequest.createProduct,product);
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
    },
    readProduct: async (pageNum : number)=>{
        const {data} = await ApiCommon.loginJsonAPI.get(`${productRequest.readProduct}/${pageNum}`)
        return data
    },
    readDetailProduct : async (productId : number) => {
        const url =`${productURL}/${productId}`;
        console.log(url)
        const {data} = await ApiCommon.loginJsonAPI.get(url);
        return data;
    }
}