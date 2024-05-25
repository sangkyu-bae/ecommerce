import React, {useEffect} from 'react';
import {useForm} from "react-hook-form";
import useCustomQuery from "@/shared/hook/useCustomQuery";

type Form = {
    initData: any,
    onSubmit: any,
    validation: object
}

function UseFormHook({initData, onSubmit, validation}) {

    const {submitMutation} = useCustomQuery(onSubmit);

    const {register,
        control,
        handleSubmit,
        setValue,
        getValues,
        formState: {errors}
    } = useForm({
        defaultValues: initData
    });

    useEffect(()=>{
        console.log(getValues())
    },[getValues])
    const submit = ( submitData : object) =>{
        /** validation 공통화 작업필요*/
        if(!validation){
            return;
        }
        console.log(getValues())

        // submitMutation.mutate(submitData);
    }

    return {
        register,
        handleSubmit: handleSubmit(submit),
        errors,
    }
}

export default UseFormHook;
