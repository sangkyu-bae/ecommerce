import ApiCommon from "@/shared/api/common/ApiCommon";
import {couponRequest} from "@/shared/constants/Url";

export const CouponApi = {
    readAll: async ()=>{
        const {data} = await ApiCommon.basicAPI.get(couponRequest.readAll);
        return data
    },
    registerEventCoupon:async (eventId)=>{
        const {data} = await ApiCommon.loginJsonAPI.patch(`${couponRequest.registerEventCoupon}/${eventId}`);
        return data;
    }
}