import React, {useEffect, useState} from 'react';
import GridComponent, {StyledContainer, StyledContent, StyledSetion} from "@/api/common/GridComponent";
import SideBar from "@/components/admin/sideBar";
import ProductInfo from "@/components/admin/ProductInfo";
import {useRouter} from "next/router";
import {useQuery} from "@tanstack/react-query";
import {ProductApi} from "@/api/product/ProductApi";

const ProductUpdate =()=>{
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

    useEffect(() => {
        console.log(productData)
    }, [productData])
    return(
        <StyledContainer>
            <SideBar></SideBar>
            <StyledContent>
                <StyledSetion $isproduct={true}>
                    <div className="first-section">
                        <GridComponent title={`🛒상품 수정`}></GridComponent>
                        <div className="main-section">
                            
                        </div>
                    </div>
                </StyledSetion>
            </StyledContent>
        </StyledContainer>
    )
}
export default ProductUpdate;