// import {BasketAPi} from "@/shared/api/basket/BasketAPi";
// import useCustomQuery from "@/shared/hook/useCustomQuery";
// import {useEffect, useMemo, useState} from "react";
// import {useQuery} from "@tanstack/react-query";
//
// export const useBasket =(isRead : boolean, isSubmit : boolean, isUpdate) =>{
//     const createSubmitBasket = isSubmit ? BasketAPi.create : null;
//     const readBasket = isRead ? BasketAPi.read() : null;
//     // const updateBasket = isUpdate ? BasketAPi.update : null;
//
//     const [baskets,setBaskets] = useState([]);
//     const [shouldRefetch, setShouldRefetch] = useState(isRead);
//
//     // const {submitMutation,updateMutation,data,isLoading,error} = useCustomQuery({
//     //     submit:createSubmitBasket,
//     //     queryKey:"basket",
//     //     select:readBasket,
//     //     refetch : true,
//     //     update:updateBasket
//     // });
//     const {data, isLoading, isError, error} = useQuery(
//         ["basket"],
//         () => readBasket, {
//             staleTime: 20000,
//             enabled:true,
//             onSuccess: data => {
//                 console.log("요청원료")
//                 console.log(data)
//             },
//             onError: e => {
//                 console.log(e.message)
//             }
//         }
//     )
//
//     // const queryInfo = useQuery(
//     //     ["basket"],
//     //     () => readBasket, {
//     //         staleTime: 20000,
//     //         enabled:true,
//     //         onSuccess: data => {
//     //             console.log("요청원료")
//     //             console.log(data)
//     //         },
//     //         onError: e => {
//     //             console.log(e.message)
//     //         },
//     //         retry:false
//     //     }
//     // )
//     // useEffect(()=>{
//     //     console.log(isLoading)
//     // },[isLoading])
//     //
//     // useEffect(()=>{
//     //     if(data){
//     //         if(shouldRefetch){
//     //             data.forEach(obj => obj.check = false);
//     //         }
//     //
//     //        setBaskets(data);
//     //        setShouldRefetch(false);
//     //     }
//     // },[data])
//
//     const handleQuantityChange = async (id: number, quantity: number) => {
//
//         await updateMutation.mutate({ id, quantity });
//
//         const updateBaskets = baskets.map(basket => basket.id === id ?{
//             ...basket,
//             productQuantity : quantity
//         } : basket);
//         setBaskets(updateBaskets)
//     };
//
//     const calculateTotal = () => {
//         const totalAmount = baskets.reduce((total, basket) => total + basket.price * basket.productQuantity, 0);
//         return totalAmount.toLocaleString('ko-KR');
//     };
//     return {
//         baskets,
//         setBaskets,
//         // submitMutation,
//         // data:useMemo(
//         //     () => queryInfo.data,
//         //     [queryInfo]
//         // ),
//         // isLoading,
//         // error,
//         handleQuantityChange,
//         calculateTotal
//     }
// }

import {useMutation, useQuery} from "@tanstack/react-query";
import {BasketAPi} from "@/shared/api/basket/BasketAPi";
import {useRouter} from "next/router";

export const useBasket= (isRead) =>{
    const router = useRouter()
    const submitMutation = useMutation(BasketAPi.create, {
        onMutate: variable => {
            router.push("/basket");
            console.log("onMutate", variable);
        },
        onError: (error, variable, context) => {
            console.log(error)
        },
        onSuccess: (data, variables, context) => {
            console.log(data)
        },
        onSettled: () => {
            console.log("end");
        }
    })
    const queryData =useQuery({
        context:undefined,
        queryKey : ['basket'],
        queryFn : BasketAPi.read,
        enabled : isRead,
        // select:(res) =>res.map(obj =>({ ...obj, check:false}))
        select:(res) =>res.map(basket =>({
            colorName: basket.colorName,
            id : basket.id,
            size : basket.size,
            productQuantity : basket.productQuantity,
            price : basket.price,
            productName : basket.productName,
            check:false,
            productId : basket.productId
        }))
    });

    return {
        queryData,
        submitMutation
    }
}