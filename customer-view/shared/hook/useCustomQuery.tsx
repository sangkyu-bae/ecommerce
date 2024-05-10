import React from 'react';
import {useMutation} from "@tanstack/react-query";
type Query ={
    submit:any
}
function UseCustomQuery({submit}:Query) {

    const submitMutation = useMutation(submit,{
        onMutate: variable => {
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

    return{
        submitMutation
    }
}

export default UseCustomQuery;