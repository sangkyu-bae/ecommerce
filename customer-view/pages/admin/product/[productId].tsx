import { useRouter } from 'next/router';
import GridComponent, {StyledContainer, StyledContent, StyledSetion} from "@/api/common/GridComponent";
import SideBar from "@/components/admin/sideBar";
import CardComponent from "@/components/common/CardComponent";
import Pagination from "@mui/material/Pagination";
import React, {useEffect, useState} from "react";
import {useQuery} from "@tanstack/react-query";
import {ProductApi} from "@/api/product/ProductApi";

const ProductDetail = () => {
    const router = useRouter();
    const { productId } :number = router.query;
    const [productData,setProductData] = useState<Product | undefined>(undefined);
    const {data, isLoading, isError, error} = useQuery(
        ['productData'],
        () => ProductApi.readDetailProduct(productId),{
            enabled: !!productId,
            onSuccess: data => {
                setProductData(data);
            },
            onError: e => {
                console.log(e.message);
            }
        }
    )

    useEffect(()=>{
        console.log(productData)
    },[productData])

    return (
        <StyledContainer>
            <SideBar></SideBar>
            <StyledContent>
                <StyledSetion $isproduct={true}>
                    <div className="first-section">
                        <GridComponent title={`📰${productData?.name}`}></GridComponent>
                        <div className="main-section">
                            <div className="flex">
                                <div className="image">
                                    {productData?.productImage ? productData.productImage : "image가 없습니다."}
                                </div>
                                <div className="section">
                                    <div>
                                        <span>Product Info제품정보</span>
                                    </div>
                                    <div>
                                        <span>브랜드 / 카테고리</span>
                                    </div>
                                    <div>
                                        <span>가격</span>
                                    </div>
                                    <div>
                                        <span>색상/사이즈 수량</span>
                                    </div>
                                </div>
                            </div>
                            <div>
                                {
                                    productData?.description &&
                                    <div dangerouslySetInnerHTML={{ __html :productData.description}}></div>
                                }

                            </div>
                        </div>

                    </div>
                </StyledSetion>
            </StyledContent>
        </StyledContainer>

    );
};

export default ProductDetail;