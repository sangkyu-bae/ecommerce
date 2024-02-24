import {useRouter} from "next/router";
import React, {createContext, useEffect, useState} from "react";
import {useQuery} from "@tanstack/react-query";
import {ProductApi} from "@/shared/api/product/ProductApi";
import GridComponent, {StyledContainer, StyledContent, StyledSetion} from "@/components/common/GridComponent";
import ProductInfo from "@/components/product/ProductInfo";
import {useDispatch, useSelector} from "react-redux";
import productRedux, {setProduct} from "@/store/product/productRedux";
import useProduct from "@/shared/hook/useProduct";
import {MyProduct} from "@/store/product/myProduct";
import ProductDescription from "@/components/product/ProductDescription";
import ProductImage from "@/components/product/ProductImage";
import ProductBrand from "@/components/product/ProductBrand";
import ProductPrice from "@/components/product/ProductPrice";

function DetailUserProduct(){

    const router = useRouter()
    const {mainProductId}: number = router.query;
    const {isLoading,isError,error} = useProduct(mainProductId);
    const productRedux :MyProduct = useSelector(state => state.productRedux) ;
    const compositeContext = createContext({
        counter: 0,
        counterPlus: () => {},
        counterMinus: () => {},
    });

    return (
        <ProductInfo productData={}>
            <ProductInfo.ProductImage></ProductInfo.ProductImage>
            <ProductInfo.ProductBrand></ProductInfo.ProductBrand>
            <ProductInfo.ProductPrice></ProductInfo.ProductPrice>
            <ProductInfo.ProductDescription></ProductInfo.ProductDescription>
        </ProductInfo>
        // <StyledContainer>
        //     <StyledContent isFull={true}>
        //         <StyledSetion isFull={true}>
        //             <div className="first-section">
        //                 {/*<GridComponent title={`📰${productData?.name}`}></GridComponent>*/}
        //                 {/*<div className="main-section">*/}
        //                 {/*    {*/}
        //                 {/*        productData != undefined && <ProductInfo severProductData={productData}></ProductInfo>*/}
        //                 {/*    }*/}
        //                 {/*</div>*/}
        //
        //                 <GridComponent title={`📰${productRedux?.name}`}></GridComponent>
        //                 <div className="main-section">
        //                     {
        //                         productRedux != undefined && <ProductInfo severProductData={productRedux}></ProductInfo>
        //                     }
        //                 </div>
        //             </div>
        //         </StyledSetion>
        //     </StyledContent>
        // </StyledContainer>
    )

}
ProductInfo.ProductImage = ProductImage;
ProductInfo.ProductBrand = ProductBrand;
ProductInfo.ProductPrice = ProductPrice;
ProductInfo.ProductDescription = ProductDescription;

export default DetailUserProduct;