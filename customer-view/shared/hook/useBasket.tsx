import {BasketAPi} from "@/shared/api/basket/BasketAPi";
import useCustomQuery from "@/shared/hook/useCustomQuery";
import {useEffect, useState} from "react";

export const useBasket =(isRead : boolean, isSubmit : boolean, isUpdate) =>{
    const createSubmitBasket = isSubmit ? BasketAPi.create : null;
    const readBasket = isRead ? BasketAPi.read() : null;
    const updateBasket = isUpdate ? BasketAPi.update : null;

    const [baskets,setBaskets] = useState([]);
    const [shouldRefetch, setShouldRefetch] = useState(isRead);

    const {submitMutation,updateMutation,data,isLoading,error} = useCustomQuery({
        submit:createSubmitBasket,
        queryKey:"basket",
        select:readBasket,
        refetch : true,
        update:updateBasket
    });
    useEffect(()=>{
        console.log(isLoading)
    },[isLoading])

    useEffect(()=>{
        if(data){
           setBaskets(data);
           setShouldRefetch(false);
        }
    },[data])

    const handleQuantityChange = async (id: number, quantity: number) => {

        await updateMutation.mutate({ id, quantity });

        const updateBaskets = baskets.map(basket => basket.id === id ?{
            ...basket,
            productQuantity : quantity
        } : basket);
        setBaskets(updateBaskets)
    };

    const calculateTotal = () => {
        const totalAmount = baskets.reduce((total, basket) => total + basket.price * basket.productQuantity, 0);
        return totalAmount.toLocaleString('ko-KR');
    };
    return {
        baskets,
        submitMutation,
        data,
        isLoading,
        error,
        handleQuantityChange,
        calculateTotal
    }
}