import React, {useState} from 'react';
import {useOrder} from "@/shared/hook/useOrder";

interface orderInfo {
    children: React.ReactNode
}
function OrderFetcher({children}) {

    const [pagingNm,setPagingNm] = useState<number>(1);
    const {data,isLoading,error}= useOrder(pagingNm);
    const {pageNumber = 0 ,pageSize = 0 ,totalElement = 0,totalPage = 0} = data || {};


    if(error){
        throw error;
    }

    return React.cloneElement(children,{data,isLoading,totalPage,setPagingNm});
}

export default OrderFetcher;
