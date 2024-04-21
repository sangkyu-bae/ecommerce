import {useQuery} from "@tanstack/react-query";
import {ProductApi} from "@/shared/api/product/ProductApi";
import productRedux, {initProduct, setProduct} from "@/store/product/productRedux";
import {useDispatch, useSelector} from "react-redux";

function useProduct(productId:number){

    const {data, isLoading, isError, error} = useQuery(
        ['productData'],
        () => ProductApi.readDetailProduct(productId), {
            enabled: !!productId,
            onSuccess: data => {

            },
            onError: e => {
                console.log(e.message);
            }
        }
    )


    return {isLoading,isError,error,data};
}

export default useProduct;