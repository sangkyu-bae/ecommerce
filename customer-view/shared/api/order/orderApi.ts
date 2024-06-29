import ApiCommon from "@/shared/api/common/ApiCommon";
import {orderRequest} from "@/shared/constants/Url";

export const OrderApi ={
    register: async (registerOrder: Order[])=>{
        const {data} = await ApiCommon.loginJsonAPI.post(orderRequest.register,registerOrder)
        return data;
    },
    read : async ()=>{
        const {data} = await ApiCommon.loginJsonAPI.get(orderRequest.read)
        return data
    }
}