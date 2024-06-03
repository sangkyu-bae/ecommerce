import React from 'react';
import {useMutation, useQuery} from "@tanstack/react-query";
type Query ={
    submit:any | null,
    queryKey: string | null,
    select: any | null
}
function UseCustomQuery({submit,queryKey,select}:Query) {
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

    const {data, isLoading, isError, error} = useQuery(
        [queryKey],
        () => select
    )

    return{
        submitMutation
    }
}

export default UseCustomQuery;