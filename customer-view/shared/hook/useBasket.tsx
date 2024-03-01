import {useMutation} from "@tanstack/react-query";
import MemberApi from "@/shared/api/MemberApi";
import {BasketAPi} from "@/shared/api/basket/BasketAPi";
import {setToken} from "@/shared/api/cookie/Cookie";

export const useBasket =() =>{
     const mutation = useMutation(BasketAPi.create, {
         onMutate: variable => {
             console.log("onMutate", variable);
         },
         onError: (error, variable, context) => {
             // error
             console.log(error)
         },
         onSuccess: (data, variables, context) => {
            console.log(data)

         },
         onSettled: () => {
             console.log("end");
         }
    });

     return {mutation}
}