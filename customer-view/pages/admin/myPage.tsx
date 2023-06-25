import React, {useEffect, useRef, useState} from 'react';
import SideBar, {StyledSideBar} from "@/components/admin/sideBar";
import styled from "styled-components";
import GridComponent, {StyledContent, StyledMenu, StyledSetion} from "@/api/common/GridComponent";
import TextField from "@mui/material/TextField";
import dynamic from "next/dynamic";
import Button from "@mui/material/Button";
import SendIcon from '@mui/icons-material/Send';
import {useForm} from "react-hook-form";
import {ProductApi} from "@/api/product/ProductApi";
import Validation from "@/components/common/Validation";
import {MenuItem} from "@mui/material";
import axios from "axios";
import {useMutation, useQueries, useQuery} from "@tanstack/react-query"
import {string} from "prop-types";


const NoSsrEditor = dynamic(() => import('../../components/common/' + 'ReactEdit'), {ssr: false});
const StyledContainer = styled.div`
        display : flex;
        width : 100%;
        height : 100vh;
    `

function MyPage(props) {
    const [isSuccess, setIsSuccess] = useState<boolean>(false);
    const ref = useRef<any>(null);
    const {register, handleSubmit, trigger, setValue, formState: {errors}} = useForm<ProductData>();
    const onSubmit = (productData: ProductData) => {
        console.log(productData)
        if (productData.description.length < 15) {
            alert("상품 설명을 등록하시오")
            return;
        }
        // productMutation.mutate()
    };
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
            }
        ],
    });
    useEffect(() => {
        const allQueriesSucceeded = productInfo.every(queryResult => queryResult.isSuccess);
        if (allQueriesSucceeded) {
            setIsSuccess(true)
        }
    }, [productInfo]);
    const productMutation = useMutation(ProductApi.createProduct, {
        onMutate: variable => {
            console.log("onMutate", variable);
        },
        onError: (error, variable, context) => {
            // error
            console.log(error)
        },
        onSuccess: (data, variables, context) => {
            // const loginData=data.data;
            console.log(data)
        },
        onSettled: () => {
            console.log("end");
        }
    });
    const validation = Validation;
    const onChangeDescription = () => {
        const data: string = ref.current.getInstance().getHTML();
        setValue("description", data, {shouldValidate: true});
    }

    if (isSuccess) {
        console.log(productInfo)
        const brand = productInfo[2].data;
        console.log(brand)
        return (
            <StyledContainer>
                <SideBar></SideBar>
                <StyledContent>
                    <StyledMenu></StyledMenu>
                    <StyledSetion>
                        <div className="main-section">
                            <form onSubmit={handleSubmit(onSubmit)}>
                                <GridComponent title="🛒상품 등록"></GridComponent>
                                <TextField
                                    margin="normal"
                                    required
                                    id="name"
                                    label="상품명"
                                    name="name"
                                    fullWidth
                                    autoFocus
                                    {...register("name", {
                                        ...validation.name
                                    })}
                                    error={Boolean(errors.name)}
                                    helperText={errors.name?.message}
                                />
                                <TextField
                                    type="number"
                                    margin="normal"
                                    required
                                    id="price"
                                    label="가격(원)"
                                    {...register("price", {
                                        ...validation.price
                                    })}
                                    error={Boolean(errors.price)}
                                    helperText={errors.price?.message}
                                    name="price"
                                    style={{width: '33%'}}
                                    autoFocus
                                />
                                <TextField
                                    type="number"
                                    margin="normal"
                                    style={{marginLeft: '10px', width: '32%'}}
                                    required
                                    id="price"
                                    label="수량(개)"
                                    {...register("price", {
                                        ...validation.price
                                    })}
                                    error={Boolean(errors.price)}
                                    helperText={errors.price?.message}
                                    name="price"
                                    autoFocus
                                />
                                <TextField
                                    select
                                    margin="normal"
                                    style={{marginLeft: '10px', width: '32%'}}
                                    required
                                    id="price"
                                    label="브랜드"
                                    name="brand"
                                    autoFocus
                                >
                                    {brand && brand.map((br, index) => (
                                        <MenuItem key={index} value={br.name}>
                                            {br.name}
                                        </MenuItem>
                                    ))}
                                    {/*<MenuItem value="option1">옵션 dsfasdfsdf1</MenuItem>*/}
                                    {/*<MenuItem value="option2">옵션 2</MenuItem>*/}
                                    {/*<MenuItem value="option3">옵션 3</MenuItem>*/}
                                </TextField>
                                <div>
                                    <NoSsrEditor
                                        content=""
                                        onChangeDescription={onChangeDescription}
                                        editorRef={ref}
                                        id="description"
                                        register={register}
                                    />
                                </div>
                                <Button
                                    type="submit"
                                    className="btn-submit"
                                    variant="contained"
                                    size="large"
                                    endIcon={<SendIcon/>}>
                                    상품등록
                                </Button>
                            </form>
                        </div>
                    </StyledSetion>
                </StyledContent>
            </StyledContainer>
        );
    }

}

export default MyPage;