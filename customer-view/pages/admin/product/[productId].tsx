import {useRouter} from 'next/router';
import GridComponent, {StyledContainer, StyledContent, StyledSetion} from "@/components/common/GridComponent";
import SideBar from "@/components/admin/sideBar";
import CardComponent from "@/components/common/CardComponent";
import Pagination from "@mui/material/Pagination";
import React, {useEffect, useState} from "react";
import {useQuery} from "@tanstack/react-query";
import {ProductApi} from "@/shared/api/product/ProductApi";
import Util from "@/shared/utils/CommonUtil";
import ProductInfo from "@/components/product/ProductInfo";
import ProductSizeQuantity from "@/components/product/ProductSizeQuantity";


const ProductDetail = () => {
    const router = useRouter();
    const {productId}: number = router.query;
    const [productData, setProductData] = useState<Product | undefined>(undefined);
    const {data, isLoading, isError, error} = useQuery(
        ['productData'],
        () => ProductApi.readDetailProduct(productId), {
            enabled: !!productId,
            onSuccess: data => {
                setProductData(data);
            },
            onError: e => {
                console.log(e.message);
            }
        }
    )

    return (
        <StyledContainer>
            <SideBar type={'admin'}></SideBar>
            <StyledContent>
                <StyledSetion $isproduct={true}>
                    <div className="first-section">
                        <GridComponent title={`📰${productData?.name}`}></GridComponent>
                        <div className="main-section">
                            {
                                productData != undefined &&
                                <ProductInfo productData={productData}>
                                    <ProductInfo.ProductBrand />
                                    <ProductInfo.ProductPrice />
                                    <ProductInfo.ProductDescription />
                                    <ProductInfo.ProductUpdateButton />
                                    {/*<ProductInfo.ProductSizeQuantity />*/}

                                </ProductInfo>
                            }
                        </div>
                    </div>
                </StyledSetion>
            </StyledContent>
        </StyledContainer>

    );
};

export default ProductDetail;