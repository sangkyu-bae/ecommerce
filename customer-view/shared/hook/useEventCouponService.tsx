import {useQuery} from '@tanstack/react-query';
import {QUERY_KEYS} from '@/shared/constants/queryKeys';
import {CouponApi} from "@/shared/api/coupon/CouponApi";

import { useRouter } from "next/router";
export const useEventCouponService = () => {
    const router = useRouter();

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

    const goPage = () => {
        router.push("/event/coupon");
        alert("gogogo");
    }

    const closePage = () => {

    }


    return {
        goPage: goPage,
        close: closePage,
        data : data
    }


}