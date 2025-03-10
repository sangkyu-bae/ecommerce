import React, {createContext, useContext, useEffect, useMemo, useState} from 'react';
import Util from "@/shared/utils/CommonUtil";
import {useRouter} from "next/router";
import {MyProduct} from "@/store/product/myProduct";
import ProductImage from "@/components/product/ProductImage";
import ProductBrand from "@/components/product/ProductBrand";
import ProductPrice from "@/components/product/ProductPrice";
import ProductDescription from "@/components/product/ProductDescription";
import GridComponent, {StyledContainer, StyledContent, StyledSetion} from "@/components/common/styles/GridComponent";
import ProductUpdateButton from "@/components/product/ProductUpdateButton";
import ProductSizeQuantity from "@/components/product/ProductSizeQuantity";
import ProductOrderManagement from "@/components/product/ProductOrderManagement";
import ProductTotalPay from "@/components/product/ProductTotalPay";
import {ErrorBoundary} from "@/components/error/ErrorBoundary";

interface InfoProps {
    productData: MyProduct,
    error:any
    children: React.ReactNode
}

const ProductValueContext = createContext();
const ProductActionContext = createContext();

export function useProductValueContext() {
    return useContext(ProductValueContext);
}

export function useProductActionContext(){
    return useContext(ProductActionContext);
}

function ProductInfo({productData,error ,children}: InfoProps) {
    const router = useRouter();

    const [basket,setBasket] : CreateBasket= useState({
        productSizeId : 0,
        quantity:0
    })

    const actions = useMemo(
        () => ({
            change(e) {
                const { name, value } = e.target;
                setBasket(form => (
                    { ...form,
                        [name]: value })
                );
            },
            handleUpdateButtonClick() {
                const {productId}: number = router.query;
                router.push(`/admin/product/update/${productId}`)
            },
            getBasket(){
                return basket
            }
        }),
        []
    );

    if(error){
        // return <ErrorBox></ErrorBox>
        throw error;
    }

    return (
        <ErrorBoundary>
            <ProductValueContext.Provider value ={{productData}}>
                <ProductActionContext.Provider value={actions}>
                    <StyledContainer>
                        <StyledContent isFull={true}>
                            <StyledSetion isFull={true}>
                                <div className="first-section">
                                    <GridComponent title={`📰${productData?.name}`}></GridComponent>
                                    <div className="main-section">
                                        {children}
                                    </div>
                                </div>
                            </StyledSetion>
                        </StyledContent>
                    </StyledContainer>
                </ProductActionContext.Provider>
            </ProductValueContext.Provider>
        </ErrorBoundary>
    );
}

ProductInfo.ProductImage = ProductImage;
ProductInfo.ProductBrand = ProductBrand;
ProductInfo.ProductPrice = ProductPrice;
ProductInfo.ProductDescription = ProductDescription;
ProductInfo.ProductUpdateButton = ProductUpdateButton;
ProductInfo.ProductSizeQuantity = ProductSizeQuantity;
ProductInfo.ProductOrderManagement =ProductOrderManagement;
ProductInfo.ProductTotalPay = ProductTotalPay;


export default ProductInfo;