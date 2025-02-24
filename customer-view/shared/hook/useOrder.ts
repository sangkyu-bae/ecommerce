import { UseQueryOptions, useQuery, useQueryClient } from '@tanstack/react-query';
import { OrderApi } from '@/shared/api/order/orderApi';
import { QUERY_KEYS } from '@/shared/constants/queryKeys';
import { OrderResponse } from '@/types/order';
import { toast } from 'react-toastify';

export const useOrder = (pageNum: number) => {
    const queryClient = useQueryClient();

    // 다음 페이지 프리페칭
    const prefetchNextPage = async (currentPage: number) => {
        const nextPage = currentPage + 1;
        await queryClient.prefetchQuery({
            queryKey: QUERY_KEYS.ORDERS.list(nextPage),
            queryFn: () => OrderApi.readPaging(nextPage),
        });
    };

    return useQuery<OrderResponse, Error>({
        queryKey: QUERY_KEYS.ORDERS.list(pageNum),
        queryFn: () => OrderApi.readPaging(pageNum),
        onSuccess: (data) => {
            toast.error('주문 목록을 불러오는데 실패했습니다.');
            if (data.totalPage > pageNum) {
                prefetchNextPage(pageNum);
            }
        },
        onError: (error) => {
            console.error('주문 조회 실패:', error);
            toast.error('주문 목록을 불러오는데 실패했습니다.');
        },
        select: (data) => ({
            ...data,
            orderAggregationVos: data.orderAggregationVos.map(order => ({
                ...order,
                payment: Number(order.payment) 
            }))
        }),
        staleTime: 1000 * 30 * 1,
        keepPreviousData: true,  
    });
};