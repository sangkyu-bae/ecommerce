import React from 'react';
import {useForm} from "react-hook-form";
import useCustomQuery from "@/shared/hook/useCustomQuery";

type Form = {
    initData: any,
    onSubmit: any,
    validation: any
}

function UseFormHook({initData, onSubmit, validation}:Form) {

    const {submitMutation} = useCustomQuery(onSubmit);

    const {register,
        control,
        handleSubmit,
        setValue,
        getValues,
        formState: {errors}} = useForm<Product>({
        defaultValues: initData
    });

    const submit = ( submitData : object) =>{
        /** validation 공통화 작업필요*/
        if(!validation){
            return;
        }

        submitMutation.mutate(submitData);
    }

    return {
        register,
        handleSubmit: handleSubmit(onSubmit),
        errors,
    }
}

export default UseFormHook;
