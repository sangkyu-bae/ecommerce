import ApiCommon from "@/shared/api/common/ApiCommon";
import {basketRequest} from "@/shared/constants/Url";

export const BasketAPi = {
    create: async (basket:CreateBasket) => {
        const {data} = await ApiCommon.loginJsonAPI.post(basketRequest.createBasket,basket);
        return data;
    },
    read: async ()=>{
        const {data} = await ApiCommon.loginJsonAPI.get(basketRequest.readBasket);
        return data
    }
}