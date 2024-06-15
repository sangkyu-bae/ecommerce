import {BasketAPi} from "@/shared/api/basket/BasketAPi";
import useCustomQuery from "@/shared/hook/useCustomQuery";

export const useBasket =(isRead : boolean, isSubmit : boolean) =>{
    const createSubmitBasket = isSubmit ? BasketAPi.create : null;
    const readBasket = isRead ? BasketAPi.read() : null;

    const {submitMutation,data,isLoading,error} = useCustomQuery({
        submit:createSubmitBasket,
        queryKey:"basket",
        select:readBasket
    });

    return {
        submitMutation,
        data,
        isLoading,
        error
    }
}