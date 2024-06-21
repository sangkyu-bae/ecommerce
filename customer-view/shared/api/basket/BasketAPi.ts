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
    },
    update: async ({ id, quantity }: { id: number, quantity: number })=>{
        console.log(id)
        console.log(quantity)
        const {data} = await ApiCommon.loginJsonAPI.patch(`${basketRequest.update}/${id}/${quantity}`)
        return data;
    }
}