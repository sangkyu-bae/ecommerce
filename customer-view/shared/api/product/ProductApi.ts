import ApiCommon from "@/shared/api/common/ApiCommon";
import {productRequest, productURL} from "@/shared/constants/Url";
import {useRouter} from "next/router";

export const ProductApi = {
    createProduct: async (product:Product)=>{
        const {data} = await ApiCommon.loginJsonAPI.post(productRequest.createProduct,product);
        return data;
    },
    updateProduct: async ({product, productId}:{Product,number})=>{
        const {data} = await ApiCommon.loginJsonAPI.put(`${productURL}/update/${productId}`,product);
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
        const url =`${productURL}/find/${productId}`;
        const {data} = await ApiCommon.loginJsonAPI.get(url);
        return data;
    },
    readPagingByCategory : async (categoryId:number | null, pageNum : number)=>{
        let url = `${productURL}/page/product/${pageNum}`
        if (categoryId != null) {
            url += `/${categoryId}`;
        }

        const {data} = await ApiCommon.basicAPI.get(url);
        return data;
    }
}