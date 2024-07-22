import {useEffect, useState} from "react";
import useCustomQuery from "@/shared/hook/useCustomQuery";
import {ProductApi} from "@/shared/api/product/ProductApi";
import {useQuery} from "@tanstack/react-query";

export const useProductSearch= (categoryId:number | null, pageNum: number)=>{

    const [pageInfo,setPageInfo] = useState({
        categoryId : categoryId,
        pageNum : pageNum
    });

    const {data, isLoading, isError, error,isFetching} = useQuery(
        [`searchProduct${pageInfo.pageNum}`],
        () => ProductApi.readPagingByCategory(pageInfo.categoryId,pageInfo.pageNum), {
            staleTime: 20000,
            // enabled: queryKey != null && refetch,
            enabled:true,
            onSuccess: data => {
                console.log(data)
                console.log("요청원료")
            },
            onError: e => {
                console.log(e.message)
            }
        }
    )

    const nextPage = () =>{
        const {pageNum} = pageInfo

        setPageInfo(prev => ({
            ...prev,
            pageNum:pageNum+1
        }));
    }

    const pervPage = () =>{
        const {pageNum} = pageInfo

        if(pageNum < 1){
            return;
        }

        setPageInfo(prev=>({
            ...prev,
            pageNum: pageNum-1
        }))
    }

    return {
        data:data,
        isLoading:isLoading,
        error:error,
        nextPage:nextPage,
        pervPage:pervPage
    }
}