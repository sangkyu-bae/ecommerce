import React from 'react';
import useProduct from "@/shared/hook/useProduct";
import {useRouter} from "next/router";
import Loading from "@/components/common/Loading";
interface productInfo {
    children: React.ReactNode
}
function ProductFetcher({ children }) {
    const router = useRouter()
    const {mainProductId}: number = router.query;

    const {data, isLoading,error} = useProduct(mainProductId);

    if(isLoading){
        return <Loading></Loading>
    }

    if(error){
        throw error;
    }
     return React.cloneElement(children, { data });
}

export default ProductFetcher;