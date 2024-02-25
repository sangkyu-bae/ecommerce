import {useQuery} from "@tanstack/react-query";
import {ProductApi} from "@/shared/api/product/ProductApi";
import {setProduct} from "@/store/product/productRedux";
import {useDispatch} from "react-redux";

function useProduct(productId:number){
    // const dispatch =useDispatch();

    const {data, isLoading, isError, error} = useQuery(
        ['productData'],
        () => ProductApi.readDetailProduct(productId), {
            enabled: !!productId,
            onSuccess: data => {
                console.log(data);
                // dispatch(setProduct(data))
            },
            onError: e => {
                console.log(e.message);
            }
        }
    )

    return {isLoading,isError,error,data};
}

export default useProduct;