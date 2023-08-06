import { useRouter } from 'next/router';
import GridComponent, {StyledContainer, StyledContent, StyledSetion} from "@/api/common/GridComponent";
import SideBar from "@/components/admin/sideBar";
import CardComponent from "@/components/common/CardComponent";
import Pagination from "@mui/material/Pagination";
import React from "react";
import {useQuery} from "@tanstack/react-query";
import {ProductApi} from "@/api/product/ProductApi";

const ProductDetail = () => {
    const router = useRouter();
    const { productId } :number = router.query;

    const {data, isLoading, isError, error} = useQuery(
        ['productData'],
        () => ProductApi.readDetailProduct(productId),{
            enabled: !!productId,
            onSuccess: data => {
                console.log(data)
            },
            onError: e => {
                console.log(e.message);
            }
        }
    )

    return (
        <StyledContainer>
            <SideBar></SideBar>
            <StyledContent>
                <StyledSetion $isproduct={true}>
                    <div className="first-section">
                        <GridComponent title="📰상품 정보"></GridComponent>
                        <div className="main-section">
                            <div>
                                <h1>Product Detail</h1>
                                <p>Product ID: {productId}</p>
                                {/* TODO: 상품 정보를 표시하는 컴포넌트들을 구성합니다. */}
                            </div>

                        </div>
                    </div>
                </StyledSetion>
            </StyledContent>
        </StyledContainer>

    );
};

export default ProductDetail;