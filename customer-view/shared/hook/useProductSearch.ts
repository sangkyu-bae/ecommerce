import {useEffect, useState} from "react";
import useCustomQuery from "@/shared/hook/useCustomQuery";
import {ProductApi} from "@/shared/api/product/ProductApi";

export const useProductSearch= (categoryId:number | null, pageNum: number)=>{

    const [pageInfo,setPageInfo] = useState({
        categoryId : categoryId,
        pageNum : pageNum
    });

    useEffect(()=>{
        console.log(pageInfo)
    },[pageInfo])

    const {data,isLoading,error} = useCustomQuery({
        submit:null,
        queryKey:`searchProduct${pageInfo.pageNum}`,
        select:ProductApi.readPagingByCategory(pageInfo.categoryId,pageInfo.pageNum),
        refetch : true,
        update:null
    });

    useEffect(()=>{
        console.log(data);
    },[data])

    const nextPage = (pageNum) =>{
        setPageInfo(prev => ({
            ...prev,
            pageNum:pageNum+1
        }));
    }

    return {
        data:data,
        isLoading:isLoading,
        error:error,
        nextPage:nextPage
    }
}