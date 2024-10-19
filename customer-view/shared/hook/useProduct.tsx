import {useQuery, useQueryClient} from "@tanstack/react-query";
import {ProductApi} from "@/shared/api/product/ProductApi";
import productRedux, {initProduct, setProduct} from "@/store/product/productRedux";
import {useDispatch, useSelector} from "react-redux";

function useProduct(productId:number,searchPage:string|null){
    return useQuery({
        context : undefined,
        queryKey : [`product${productId}`],
        queryFn : ()=> ProductApi.readDetailProduct(productId),
        enabled : productId !== undefined,
        select: undefined
    })
}

export default useProduct;