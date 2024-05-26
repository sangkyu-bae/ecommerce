import ApiCommon from "@/shared/api/common/ApiCommon";
import {orderRequest} from "@/shared/constants/Url";

export const OrderApi ={
    register: async (registerOrder: Order[])=>{
        console.log(registerOrder)
        console.log("?k")
        const {data} = await ApiCommon.loginJsonAPI.post(orderRequest.register,registerOrder)
        return data;
    }
}