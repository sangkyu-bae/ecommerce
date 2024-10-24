import {useQuery} from "@tanstack/react-query";
import {OrderApi} from "@/shared/api/order/orderApi";

export const useOrder = (pageNum : number)=>{

    return useQuery({
            context:undefined,
            queryKey : [`order:${pageNum}`],
            staleTime: 20000,
            queryFn : ()=>OrderApi.readPaging(pageNum),
            enabled : true,
            select:undefined
        });
}
