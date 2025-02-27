import { UseQueryOptions, useQuery, useQueryClient } from '@tanstack/react-query';
import { QUERY_KEYS } from '@/shared/constants/queryKeys';
import {CouponApi} from "@/shared/api/coupon/CouponApi";
export const useEventCouponService =()=>{
    const queryClient = useQueryClient();

    const {data, isLoading, isError, error} = useQuery(
        QUERY_KEYS.COUPON.key,
        () => CouponApi.readAll(), {
            staleTime: 20000,
            enabled: true,
            onSuccess: data => {
                console.log("요청원료")
                console.log(data)
            },
            onError: e => {
                console.log(e.message)
            }
        }
    )


    return {
        couponData : data,
        isLoading,
        error
    }




}