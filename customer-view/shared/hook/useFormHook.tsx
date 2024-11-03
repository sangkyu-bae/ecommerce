import React, {useEffect} from 'react';
import {useForm} from "react-hook-form";
import useCustomQuery from "@/shared/hook/useCustomQuery";
import {OrderProduct} from "@/store/product/myProduct";
import {OrderService} from "@/shared/service/orderService";
import {useRouter} from "next/router";

type FormProps = {
    initData: Record<string, any>,
    onSubmit: (data: any) => void,
    validation?: object,
    products : OrderProduct[]
}


function UseFormHook({initData, onSubmit, validation,products}) {
    const orderService = new OrderService();
    const router = useRouter();
    const {submitMutation} = useCustomQuery({
        submit:onSubmit
    });

    const {register,
        control,
        handleSubmit,
        setValue,
        getValues,
        formState: {errors}
    } = useForm({
        defaultValues: initData
    });

    const submit = (submitData : object) =>{


        const registerOrderProducts : RegisterOrder[]=
            products.map(product => orderService.mapToOrder(product));

        const registerOrder : Order = {
            products : registerOrderProducts,
            address:submitData.address,
            phone: submitData.phone
        } ;

        submitMutation.mutate(registerOrder);
        router.push('/order/list')

    }

    useEffect(() => {
        if (initData) {
            setValue('initData', initData);
        }
    }, [initData, setValue]);
    return {
        register,
        handleSubmit: handleSubmit(submit),
        errors,
    }
}

export default UseFormHook;
