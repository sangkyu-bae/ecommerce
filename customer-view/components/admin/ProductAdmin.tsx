import React, {useEffect, useRef, useState} from 'react';
import SideBar from "@/components/admin/sideBar";
import GridComponent, {StyledContent, StyledMenu, StyledSetion} from "@/api/common/GridComponent";
import TextField from "@mui/material/TextField";
import Input from "@/components/admin/Input";
import Button from "@mui/material/Button";
import SendIcon from "@mui/icons-material/Send";
import dynamic from "next/dynamic";
import styled from "styled-components";
import {string} from "prop-types";
import {useForm} from "react-hook-form";
import {useMutation, useQueries} from "@tanstack/react-query";
import {ProductApi} from "@/api/product/ProductApi";
import Validation from "@/utils/Validation";
import SizeContainer from "@/components/admin/SizeContainer";
import {useRouter} from "next/router";

const NoSsrEditor = dynamic(() => import('../../components/common/' + 'ReactEdit'), {ssr: false});
const StyledContainer = styled.div`
        display : flex;
        width : 100%;
        height : 100vh;
    `
type ProductAdmin ={
    isCreate:boolean,
    title : string,
    buttonTitle:string,
    severProductData : Product
}

function ProductAdmin({isCreate,title,buttonTitle,severProductData}:ProductAdmin) {
    const router = useRouter();
    const {productId}: number = router.query;
    const [isSuccess, setIsSuccess] = useState<boolean>(false);
    const cnt : number = isCreate ? 1 : severProductData.colorProductDtoList.length;
    const [colorCnt, setColorCnt] = useState<number>(cnt);
    const [colorObject, setColorObject] = useState<ColorData[]>([]);

    useEffect(()=>{
        if(!isCreate){
            const data : string =severProductData.description;
            setValue("description", data, {shouldValidate: true});
        }
    },[])

    const handleChangeColorData = (colorData: ColorData, type: string) => {
        if (type == 'add') {
            addColorData(colorData);
        } else if (type == 'remove') {
            removeData(colorData);
        }
    }

    const addColorData = (colorData: ColorData) => {
        let color = colorObject.filter(obj => obj.colorName == colorData.colorName);
        if (color) {
            let colorDatas = colorObject.filter(obj => obj.colorName != colorData.colorName);
            colorDatas.push(colorData);
            setColorObject(colorDatas)
        } else {
            setColorObject([...colorObject, colorData]);
        }
    }

    const removeData = (colorData: ColorData) => {
        let colorDatas = colorObject.filter(obj => obj.colorName != colorData.colorName);
        setColorObject(colorDatas);
    }

    const ref = useRef<any>(null);
    const {register, handleSubmit, watch, setValue, formState: {errors}} = useForm<ProductData>();


    const onSubmit = (productData: ProductData) => {
        if (productData.description.length < 15) {
            alert("상품 설명을 등록하시오")
            return;
        }
        if (!validation.colorValidate(colorObject)) {
            alert("색상정보를 확인하세요.");
            return;
        }
        const createProduct: ProductData = createProductObj(productData);

        isCreate ?
            productMutation.mutate(productData):
            productMutation.mutate({ product: createProduct, productId: severProductData.id});
    };
    const createProductObj = (productData: ProductData): ProductData => {
        productData.colorDataList = colorObject;

        let tranceProductData: ProductData = Object.keys(productData).reduce((acc: ProductData, key: string) => {
            if (!key.startsWith('color_')) {
                acc[key] = productData[key];
            }
            return acc;
        }, {} as ProductData);

        return tranceProductData;
    }

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

    const api = isCreate ? ProductApi.createProduct:ProductApi.updateProduct
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

    useEffect(() => {
        const allQueriesSucceeded = productInfo.every(queryResult => queryResult.isSuccess);
        if (allQueriesSucceeded) {
            setIsSuccess(true)
        }
    }, [productInfo]);

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
            if (element == 'plus' && colorCnt < color.length) {
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
                    colorCnt={colorCnt}
                    index={index}
                    handleChangeColorData={handleChangeColorData}
                    register={register}
                    errors={errors}
                    colorProductData = {!isCreate ? severProductData.colorProductDtoList[index] : null}
                />
            ));
        };
        return (
            <StyledContainer>
                <SideBar></SideBar>
                <StyledContent>
                    <StyledMenu></StyledMenu>
                    <StyledSetion isproduct={false}>
                        <div className="main-section">
                            <form onSubmit={handleSubmit(onSubmit)}>
                                <GridComponent title={title}></GridComponent>
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
                                    defaultValue={!isCreate ? severProductData.name : null}
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
                                    defaultValue={!isCreate ? severProductData.price : null}
                                    autoFocus
                                />

                                <Input names={category}
                                       title="category"
                                       width={32}
                                       marginLeft={2}
                                       register={register}
                                       errors={errors}
                                       value = {!isCreate ? severProductData.categoryDto.id : 0}
                                />

                                <Input names={brand}
                                       title="brand"
                                       width={32}
                                       marginLeft={2}
                                       register={register}
                                       errors={errors}
                                       value = {!isCreate ? severProductData.brandDto.id : 0 }
                                />
                                {
                                    renderComponents()
                                }
                                <div>
                                    <NoSsrEditor
                                        content={!isCreate ? severProductData.description :""}
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
                                    {buttonTitle}
                                </Button>
                            </form>
                        </div>
                    </StyledSetion>
                </StyledContent>
            </StyledContainer>
        );
    }
}

export default ProductAdmin;