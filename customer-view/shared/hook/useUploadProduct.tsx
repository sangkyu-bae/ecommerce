import React, {useEffect, useState} from 'react';
import Validation from "@/shared/utils/validation/Validation";
import {useFieldArray, useForm} from "react-hook-form";
import {useMutation, useQueries} from "@tanstack/react-query";
import {ProductApi} from "@/shared/api/product/ProductApi";
import {string} from "prop-types";
import validation from "@/shared/utils/validation/Validation";
import {useRouter} from "next/router";

function useUploadProduct({productData,ref,submit,type} ) {
    const[isLoading,setIsLoading] = useState(true)
    const router = useRouter();

    const { register,control, handleSubmit, setValue, getValues, formState: { errors } } = useForm<Product>({
        defaultValues: productData
    });
    const { fields, append, remove, update} = useFieldArray({
        control,
        name: "productComponents"
    });

    const addProductComponent = () =>{
        const productComponent = {
            color: {},
            sizes:[]
        }
        append(productComponent);
    }

    const updateProductComponent = (index,updateData,type) => {
        let updateIndexProductComponent = getValues().productComponents[index];

        if(type == 'color'){
            updateIndexProductComponent.color = updateData;
        }else if(type == 'size'){
            updateIndexProductComponent.sizes = updateData;
        }

        update(index, updateIndexProductComponent);
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
            addProductComponent()
        }
    },[isLoading])



    const onChangeDescription = () => {
        const data: string = ref.current.getInstance().getHTML();
        setValue("description", data, {shouldValidate: true});
    }


    const productMutation = useMutation(submit, {
        onMutate: variable => {
            console.log("onMutate", variable);
        },
        onError: (error, variable, context) => {
            console.log(error)
        },
        onSuccess: (data, variables, context) => {
            console.log(data)
            router.push("/admin/product")
        },
        onSettled: () => {
            console.log("end");
        }
    })

    const onSubmit = (productData: Product) => {

        if(!validation.colorValueValidate(productData.productComponents)){
            alert("상품 사이즈를 확인하세요")
        }
        if (productData.description.length < 15) {
            alert("상품 설명을 등록하시오")
            return;
        }


        productData.category = {
            id:productData.category
        }

        productData.brand = {
            id : productData.brand
        }

        if(type == "update"){
            const productId = productData.id
            delete productData.id
            delete productData.aggregateIdentifier

            productMutation.mutate({
                product: productData,
                productId: productId
            });
        }else{
            delete productData.id
            productMutation.mutate(productData);
        }
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