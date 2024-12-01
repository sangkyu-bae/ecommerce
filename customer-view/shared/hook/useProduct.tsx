import {useQuery, useQueryClient} from "@tanstack/react-query";
import {ProductApi} from "@/shared/api/product/ProductApi";
import productRedux, {initProduct, setProduct} from "@/store/product/productRedux";
import {useDispatch, useSelector} from "react-redux";
import {useEffect} from "react";

function useProduct(productId:number){
    return useQuery({
        context : undefined,
        queryKey : [`product${productId}`],
        queryFn : ()=> ProductApi.readDetailProduct(productId),
        enabled : productId !== undefined,
        select: undefined,
        suspense:false,
        onError: (error: any) => {
            console.error("Product fetch failed:", error);
        }
    })
}

export default useProduct;