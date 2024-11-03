import React from 'react';
import {useQuery} from "@tanstack/react-query";
import {ProductApi} from "@/shared/api/product/ProductApi";

export const useProductTest=()=>{

    return useQuery(
        {
            context:undefined,
            queryKey : ['basicProduct'],
            queryFn : ProductApi.readProduct(1),
            enabled:true
        }
    );
}