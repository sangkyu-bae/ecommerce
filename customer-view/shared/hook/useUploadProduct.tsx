import React, {useEffect, useState} from 'react';
import Validation from "@/utils/Validation";
import {useFieldArray, useForm} from "react-hook-form";
import {useMutation, useQueries} from "@tanstack/react-query";
import {ProductApi} from "@/shared/api/product/ProductApi";
import {string} from "prop-types";

function useUploadProduct({productData,ref} ) {
    const[isLoading,setIsLoading] = useState(true)

    const { register,control, handleSubmit, setValue, getValues, formState: { errors } } = useForm<Product>({
        defaultValues: productData
    });
    const { fields, append, remove, update} = useFieldArray({
        control,
        name: "productComponents"
    });

    const addProductComponent = (color) =>{
        const productComponent = {
            color:color,
            sizes:[]
        }

        console.log(productComponent)
        append(productComponent);
    }

    const updateProductComponent = (index,updateData) => {
        update(index, updateData);
    }

    const removeProductComponent = (index) =>{
        remove(index);
    }

    const productInfo = useQueries({
        queries: [
            {
                queryKey: ['brand'],
                queryFn: () => ProductApi.readAllBrand(),
                staleTime: 5 * 60 * 1000, // 5분 동안 캐시된 데이터 사용
                cacheTime: 30 * 60 * 1000 // 30분 동안 캐시 데이터 유지
            },
            {
                queryKey: ['category'],
                queryFn: () => ProductApi.readAllCategory(),
                staleTime: 5 * 60 * 1000, // 5분 동안 캐시된 데이터 사용
                cacheTime: 30 * 60 * 1000 // 30분 동안 캐시 데이터 유지
            },
            {
                queryKey: ['color'],
                queryFn: () => ProductApi.readAllColor(),
                staleTime: 5 * 60 * 1000, // 5분 동안 캐시된 데이터 사용
                cacheTime: 30 * 60 * 1000 // 30분 동안 캐시 데이터 유지
            },
            {
                queryKey: ['size'],
                queryFn: () => ProductApi.readAllSize(),
                staleTime: 5 * 60 * 1000, // 5분 동안 캐시된 데이터 사용
                cacheTime: 30 * 60 * 1000 // 30분 동안 캐시 데이터 유지
            }
        ],
    });

    useEffect(()=>{
        let isSuccess = true;

        productInfo.forEach(product=>{
            if(product.status != "success"){
                isSuccess = false;
            }
        })

        setIsLoading(!isSuccess);

    },[productInfo])

    useEffect(()=>{
        if(!isLoading){
            addProductComponent(productInfo[2].data[0])
        }
    },[isLoading])



    const onChangeDescription = () => {
        console.log(getValues())
        const data: string = ref.current.getInstance().getHTML();
        setValue("description", data, {shouldValidate: true});
    }

    const api = ProductApi.createProduct;

    const productMutation = useMutation(api, {
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

    const onSubmit = (productData: Product) => {
        if (productData.description.length < 15) {
            alert("상품 설명을 등록하시오")
            return;
        }

        productMutation.mutate(productData);
    };

    return {
        register,
        handleSubmit: handleSubmit(onSubmit),
        errors,
        onChangeDescription,
        productInfo,
        isLoading,
        addProductComponent,
        updateProductComponent,
        removeProductComponent,
        getValues
    };
}

export default useUploadProduct;