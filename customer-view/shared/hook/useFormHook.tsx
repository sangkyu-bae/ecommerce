import React from 'react';
import {useForm} from "react-hook-form";

type Form = {
    initData: any,
    onSubmit: any
}

function UseFormHook({
                     initData,
                     onSubmit
                 }: Form) {

    const {register, control, handleSubmit, setValue, getValues, formState: {errors}} = useForm<Product>({
        defaultValues: initData
    });

    return {
        register,
        handleSubmit: handleSubmit(onSubmit),
        errors,
    }
}

export default UseFormHook;
