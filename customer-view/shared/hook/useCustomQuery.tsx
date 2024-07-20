import React, {useEffect, useState} from 'react';
import {useMutation, useQuery} from "@tanstack/react-query";

type Query = {
    submit: any | null,
    queryKey: string | null,
    select: any | null,
    refetch:boolean
    update :any |null
}

function UseCustomQuery({
                            submit,
                            queryKey,
                            select,
                            refetch,
                            update
                        }: Query) {

    const submitMutation = useMutation(submit, {
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

    const updateMutation = useMutation(update, {
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
        () => select, {
            // staleTime: 60000,
            // enabled: queryKey != null && refetch,
            onSuccess: data => {
                console.log("요청원료")
                console.log(data)
            },
            onError: e => {
                console.log(e.message)
            }
        }
    )
    useEffect(()=>{
        console.log(data);
    },[data])

    return {
        submitMutation,
        updateMutation,
        data,
        isLoading,
        isError,
        error
    }
}

export default UseCustomQuery;