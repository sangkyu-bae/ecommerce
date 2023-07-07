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
import {FormGroup, MenuItem} from "@mui/material";
import axios from "axios";
import {QueryClient, useMutation, useQueries, useQuery, useQueryClient} from "@tanstack/react-query"
import {string} from "prop-types";
import FormControlLabel from "@mui/material/FormControlLabel";
import Checkbox from "@mui/material/Checkbox";
import Input from "@/components/admin/Input";
import SizeContainer from "@/components/admin/SizeContainer";


const NoSsrEditor = dynamic(() => import('../../components/common/' + 'ReactEdit'), {ssr: false});
const StyledContainer = styled.div`
        display : flex;
        width : 100%;
        height : 100vh;
    `

function MyPage() {

    const [isSuccess, setIsSuccess] = useState<boolean>(false);
    const [colorCnt, setColorCnt] = useState<number>(1);

    const ref = useRef<any>(null);
    const {register, handleSubmit, trigger, setValue, formState: {errors}} = useForm<ProductData>();
    const onSubmit = (productData: ProductData) => {
        // console.log(productData)
        const colors = Object.keys(productData)
            .filter(key => key.startsWith('color_'))
            .map(key => productData[key]);

         let tranceProductData = Object.keys(productData).reduce((acc:ProductData, key:string) => {
            if (!key.startsWith('color_')) {
                acc[key] = productData[key];
            }
            return acc;
        }, {});

        tranceProductData={
            ...tranceProductData,
            colors:colors
        }
        console.log(tranceProductData);
        // if (productData.description.length < 15) {
        //     alert("상품 설명을 등록하시오")
        //     return;
        // }
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
            },
            {
                queryKey: ['size'],
                queryFn: () => ProductApi.readAllSize()
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
        const brand = productInfo[0].data;
        const category = productInfo[1].data;
        const color = productInfo[2].data;
        const size = productInfo[3].data;

        const setSizeColor = (element: String) => {
            if (element == 'plus' && colorCnt < color.length ) {
                setColorCnt(colorCnt + 1);
            } else if (element == 'remove' && colorCnt > 1) {
                setColorCnt(colorCnt - 1);
            }
        }

        const renderComponents = () => {
            return Array.from({length: colorCnt}).map((_, index) => (
                <SizeContainer
                    key={index}
                    sizes={size}
                    colors={color}
                    setSizeColor={setSizeColor}
                    colorCnt = {colorCnt}
                    index={index}
                    register={register}
                    errors={errors}
                />
            ));
        };
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
                                    style={{width: '32%'}}
                                    autoFocus
                                />

                                <Input names={category}
                                       title="category"
                                       width={32}
                                       marginLeft={2}
                                       register={register}
                                       errors={errors}
                                />

                                <Input names={brand}
                                       title="brand"
                                       width={32}
                                       marginLeft={2}
                                       register={register}
                                       errors={errors}/>
                                {
                                    renderComponents()
                                }
                                <div>
                                    <NoSsrEditor
                                        content=""
                                        onChangeDescription={onChangeDescription}
                                        editorRef={ref}
                                        id="description"
                                        register={register}
                                        erros
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