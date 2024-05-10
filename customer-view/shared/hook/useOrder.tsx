import React from 'react';
import useCustomQuery from "@/shared/hook/useCustomQuery";
import {useForm} from "react-hook-form";
import useFormHook from "@/shared/hook/useFormHook";
type Order={

}
function UseOrder(props) {

    const {submitMutation}=useCustomQuery({
        submit:'a'
    });

    const {register,handleSubmit,errors} = useFormHook({
        initData:'b',
        onSubmit:submitMutation
    })

    return {

    }
}

export default UseOrder;
