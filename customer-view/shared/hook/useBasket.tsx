import {useMutation} from "@tanstack/react-query";
import MemberApi from "@/shared/api/MemberApi";
import {BasketAPi} from "@/shared/api/basket/BasketAPi";
import {setToken} from "@/shared/api/cookie/Cookie";
import useCustomQuery from "@/shared/hook/useCustomQuery";

export const useBasket =() =>{
    const {submitMutation} = useCustomQuery({
        submit:BasketAPi.create,
        queryKey:"basket",
        select:BasketAPi.read
    });
}