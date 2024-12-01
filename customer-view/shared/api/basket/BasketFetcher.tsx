import React from 'react';
import {useBasket} from "@/shared/hook/useBasket";

interface basketInfo {
    children: React.ReactNode
}
function BasketFetcher({children}) {

    const {queryData} = useBasket(true);
    const {data} = queryData;

    return React.cloneElement(children,{data});
}

export default BasketFetcher;
