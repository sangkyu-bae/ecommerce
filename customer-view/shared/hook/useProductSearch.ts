import {useEffect, useState} from "react";
import useCustomQuery from "@/shared/hook/useCustomQuery";
import {ProductApi} from "@/shared/api/product/ProductApi";
import {useQuery} from "@tanstack/react-query";

export const useProductSearch= (categoryId:number | null,
                                pageNum: number
                                )=>{

    const [pageInfo,setPageInfo] = useState({
        categoryId : categoryId,
        pageNum : pageNum,
        totalPage :0
    });

    const {data, isLoading, isError, error,isFetching} = useQuery(
        [`searchProduct`,`${pageInfo.pageNum}`],
        () => ProductApi.readPagingByCategory(pageInfo.categoryId,pageInfo.pageNum), {
            staleTime: 20000,
            enabled:true,
            onSuccess: data => {
                setPageInfo(prev =>({
                    ...prev,
                    totalPage:data.totalPage
                }))
            },
            onError: e => {
                console.log(e.message)
            }
        }
    )

    const movePage = (value) =>{
        setPageInfo(prev => ({
            ...prev,
            pageNum: value
        }))
    }

    const getTotalPage = () =>{
        const {totalPage} = pageInfo;

        if(totalPage < 0){
            return 0;
        }

        return totalPage;
    }

    const getPage = () =>{
        const {pageNum} = pageInfo;

        return pageNum;
    }

    return {
        data:data,
        isLoading:isLoading,
        error:error,
        movePage:movePage,
        getTotalPage:getTotalPage,
        getPage:getPage
    }
}