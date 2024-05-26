import React, {useEffect} from 'react';
import {useForm} from "react-hook-form";
import useCustomQuery from "@/shared/hook/useCustomQuery";

type FormProps = {
    initData: Record<string, any>,
    onSubmit: (data: any) => void,
    validation?: object,
    dataParsingEvent?: (data: any) => any
}

function UseFormHook({initData, onSubmit, validation,dataParsingEvent}) {
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
        /** validation 공통화 작업필요*/
        if(!validation){
            return;
        }

        if(dataParsingEvent != null){
            submitData = dataParsingEvent(submitData);
        }

        console.log(submitData)
        submitMutation.mutate(submitData);
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
