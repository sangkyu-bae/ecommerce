import React, {useEffect} from 'react';
import {useQuery} from "@tanstack/react-query";
import {RankApi} from "@/shared/api/RankApi";

interface mainInfo{
    children: React.ReactNode
}

function MainFetcher({children}) {
    const {data,isLoading,error}=useQuery({
        context : undefined,
        queryKey : ['clickRank'],
        queryFn : () => RankApi.readClickRank(),
        select:undefined,
        enabled:true
    })

    if(error){
        throw error;
    }

    if(!isLoading){
        return React.cloneElement<object>(children,{data});
    }

}

export default MainFetcher;