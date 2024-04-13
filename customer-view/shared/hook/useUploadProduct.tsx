import React, {useEffect, useState} from 'react';
import Validation from "@/utils/Validation";
import {useForm} from "react-hook-form";
import {useMutation, useQueries} from "@tanstack/react-query";
import {ProductApi} from "@/shared/api/product/ProductApi";
import {string} from "prop-types";

type IProduct ={
    productData : string,
    ref:any
}
function useUploadProduct({productData,ref} ) {
    const { register, handleSubmit, watch, setValue, formState: { errors } } = useForm<Product>({
        defaultValues: productData
    });

    const[isLoading,setIsLoading] = useState(true)

    const productInfo = useQueries({
        queries: [
            {
                queryKey: ['brand'],
                queryFn: () => ProductApi.readAllBrand()
            },
            {
                queryKey: ['category'],
                queryFn: () => ProductApi.readAllCategory()
            },
            {
                queryKey: ['color'],
                queryFn: () => ProductApi.readAllColor()
            },
            {
                queryKey: ['size'],
                queryFn: () => ProductApi.readAllSize()
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

    const onChangeDescription = () => {
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
    });
    const validation = Validation;

    const addColorData = (colorData: ColorData) => {
        let color = colorObject.filter(obj => obj.colorDto.name == colorData.colorDto.name);
        if (color) {
            let colorDatas = colorObject.filter(obj => obj.colorDto.name != colorData.colorDto.name);
            colorDatas.push(colorData);
            setColorObject(colorDatas)
        } else {
            setColorObject([...colorObject, colorData]);
        }
    }

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
        isLoading
    };
}

export default useUploadProduct;