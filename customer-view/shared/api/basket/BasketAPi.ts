import ApiCommon from "@/shared/api/common/ApiCommon";
import {basketRequest} from "@/shared/constants/Url";
import {getAccessToken} from "@/shared/api/cookie/Cookie";
const accessToken = getAccessToken()
export const BasketAPi = {
    create: async (baskets:CreateBasket[]) => {
        const {data} = await ApiCommon.loginJsonAPI.post(basketRequest.createBasket,baskets);
        return data;
    },
    read: async ()=>{
        const {data} = await ApiCommon.loginJsonAPI.get(basketRequest.readBasket);
        return data
    }
}