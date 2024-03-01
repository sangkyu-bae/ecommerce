import ApiCommon from "@/shared/api/common/ApiCommon";
import {basketRequest} from "@/shared/constants/Url";

export const BasketAPi = {
    create: async (basket:CreateBasket) => {
        const {data} = await ApiCommon.loginAPI.post(basketRequest.createBasket,basket);
        return data;
    }
}