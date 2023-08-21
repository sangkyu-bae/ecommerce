import {useRouter} from 'next/router';
import GridComponent, {StyledContainer, StyledContent, StyledSetion} from "@/api/common/GridComponent";
import SideBar from "@/components/admin/sideBar";
import CardComponent from "@/components/common/CardComponent";
import Pagination from "@mui/material/Pagination";
import React, {useEffect, useState} from "react";
import {useQuery} from "@tanstack/react-query";
import {ProductApi} from "@/api/product/ProductApi";
import Util from "@/utils/CommonUtil";
import ProductInfo from "@/components/admin/ProductInfo";


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
                                productData != undefined && <ProductInfo severProductData={productData}></ProductInfo>
                            }
                        </div>
                    </div>
                </StyledSetion>
            </StyledContent>
        </StyledContainer>

    );
};

export default ProductDetail;