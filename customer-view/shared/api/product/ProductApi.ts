import ApiCommon from "@/shared/api/common/ApiCommon";
import {productRequest, productURL} from "@/shared/constants/Url";
import {useRouter} from "next/router";

export const ProductApi = {
    createProduct: async (product:Product)=>{
        console.log(product)
        const {data} = await ApiCommon.loginJsonAPI.post(productRequest.createProduct,product);
        return data;
    },
    updateProduct: async ({product, productId}:{Product,number})=>{
        console.log(productId)
        console.log(product)
        const {data} = await ApiCommon.loginJsonAPI.put(`${productURL}/${productId}`,product);
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
        console.log("?")
        const url =`${productURL}/find/${productId}`;
        console.log(url)
        const {data} = await ApiCommon.loginJsonAPI.get(url);
        return data;
    }
}