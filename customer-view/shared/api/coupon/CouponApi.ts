import ApiCommon from "@/shared/api/common/ApiCommon";
import {couponRequest} from "@/shared/constants/Url";
import {getAccessToken} from "@/shared/api/cookie/Cookie";

const token = getAccessToken();
export const CouponApi = {
    readAll: async ()=>{
        let res;
        if(token){
           const {data} = await ApiCommon.loginJsonAPI.get(couponRequest.readAllAuth);
           res = data;
        }else{
            const {data} = await ApiCommon.basicAPI.get(couponRequest.readAll);
            res = data;
        }

        return res
    },
    registerEventCoupon:async (eventId)=>{
        try{
            const {data} = await ApiCommon.loginJsonAPI.patch(`${couponRequest.registerEventCoupon}/${eventId}`);
            return data;
        }catch (e){
            return e.response.data;
        }

    }
}