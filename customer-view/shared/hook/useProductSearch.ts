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
    const startTime = performance.now();
    const {data, isLoading, isError, error,isFetching} = useQuery(
        [`searchProduct`,`${pageInfo.pageNum}`],
        () => ProductApi.readPagingByCategory(pageInfo.categoryId,pageInfo.pageNum), {
            staleTime: 0,
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

    // useEffect(() => {
    //     if (data && !isLoading && !isFetching) {
    //         const startTime = performance.now();
    //
    //         // 이 시점에서 렌더링이 진행됩니다.
    //         console.log("캐시된 데이터:", data);
    //
    //         const endTime = performance.now();
    //         console.log("캐시된 데이터 렌더링 시간:", endTime - startTime, "ms");
    //     }
    // }, [data, isLoading, isFetching]);

    const endTime = performance.now();
    const renderTime = endTime - startTime;

    console.log(`렌더링 시간: ${renderTime} ms`);
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