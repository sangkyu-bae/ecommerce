import React, {useEffect} from 'react';
import {useQueries, useQuery} from "@tanstack/react-query";
import { QUERY_KEYS } from '@/shared/constants/queryKeys';
import {RankApi} from "@/shared/api/RankApi";
import {CouponApi} from "@/shared/api/coupon/CouponApi";

interface mainInfo{
    children: React.ReactNode
}

function MainFetcher({children}) {
    const results = useQueries({
        queries: [
            {
                queryKey: QUERY_KEYS.COUPON.key,
                queryFn : () => CouponApi.readAll(),
                staleTime: 200,
                enabled: true,
                suspense: true
            },
            {
                context : undefined,
                queryKey : ['clickRank'],
                queryFn : () => RankApi.readClickRank(),
                select:undefined,
                enabled:true,
                staleTime: 200,
                suspense: true
            }
        ]
    });

    const error = results.find(result => result.isError)?.error || null;
    const isLoading = results.some(result => result.isLoading);
    const data = results.map(result => result.data);

    if(error){
        throw error;
    }
    if(!isLoading){
        return React.cloneElement<object>(children);
    }

}

export default MainFetcher;