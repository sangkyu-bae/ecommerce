import {useQuery, useQueryClient} from "@tanstack/react-query";
import {ProductApi} from "@/shared/api/product/ProductApi";
import productRedux, {initProduct, setProduct} from "@/store/product/productRedux";
import {useDispatch, useSelector} from "react-redux";

function useProduct(productId:number,searchPage:string|null){

    // const {data, isLoading, isError, error} = useQuery(
    //     ['productData'],
    //     () => ProductApi.readDetailProduct(productId), {
    //         enabled: !!productId,
    //         staleTime:20000,
    //
    //         onSuccess: data => {
    //
    //         },
    //         onError: e => {
    //             console.log(e.message);
    //         }
    //     }
    // )
    const queryClient = useQueryClient();
    const { data, isLoading, isError, error } = useQuery(
        ['productData', productId],
        () => ProductApi.readDetailProduct(productId),
        {
            initialData: () => {

                const productList = queryClient
                    .getQueryData(['searchProduct',`${searchPage}`])?.productVoList;

                if(!productList){
                    return undefined
                }
                const product = productList.find((p) => p.id == productId);

                console.log(product)
                if (product) {
                    return product
                } else {
                    return undefined;
                }
            },
            enabled: !!productId,
            staleTime: 20000,
            onSuccess: (data) => {
                console.log(`chcch? ${data}`)
                // Success handler
            },
            onError: (e) => {
                console.log(e.message);
            },
        }
    );


    return {isLoading,isError,error,data};
}

export default useProduct;