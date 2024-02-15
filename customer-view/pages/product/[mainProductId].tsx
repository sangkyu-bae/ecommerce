import {useRouter} from "next/router";
import React, {useEffect, useState} from "react";
import {useQuery} from "@tanstack/react-query";
import {ProductApi} from "@/api/product/ProductApi";
import GridComponent, {StyledContainer, StyledContent, StyledSetion} from "@/api/common/GridComponent";
import ProductInfo from "@/components/admin/ProductInfo";

function DetailUserProduct(){

    const router = useRouter()
    const {mainProductId}: number = router.query;
    const [productData, setProductData] = useState<Product | undefined>(undefined);
    const {data, isLoading, isError, error} = useQuery(
        ['productData'],
        () => ProductApi.readDetailProduct(mainProductId), {
            enabled: !!mainProductId,
            onSuccess: data => {
                setProductData(data);
            },
            onError: e => {
                console.log(e.message);
            }
        }
    )

    useEffect(()=>{
        console.log(productData);
    },[productData])

    return (
        <StyledContainer>
            {/*<StyledContent>*/}
            {/*    <StyledSetion $isproduct={true}>*/}
            {/*        <div className="first-section">*/}
            {/*            <GridComponent title={`📰${productData?.name}`}></GridComponent>*/}
            {/*            <div className="main-section">*/}
            {/*                {*/}
            {/*                    productData != undefined && <ProductInfo severProductData={productData}></ProductInfo>*/}
            {/*                }*/}
            {/*            </div>*/}
            {/*        </div>*/}
            {/*    </StyledSetion>*/}
            {/*</StyledContent>*/}
        </StyledContainer>
    )

}

export default DetailUserProduct;