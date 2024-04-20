import React, {useEffect, useState} from 'react';
import GridComponent, {StyledContainer, StyledContent, StyledSetion} from "@/components/common/GridComponent";
import SideBar from "@/components/admin/sideBar";
import ProductInfo from "@/components/product/ProductInfo";
import {useRouter} from "next/router";
import {useQuery} from "@tanstack/react-query";
import {ProductApi} from "@/shared/api/product/ProductApi";
import ProductAdmin from "@/viewer/ProductAdmin";
import UploadProductComponent from "@/viewer/UploadProductComponent";

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
                console.log(data)
            },
            onError: e => {
                console.log(e.message);
            }
        }
    )

    return(
        productData &&
            <UploadProductComponent
                title="🛒상품 수정"
                buttonTitle="상품수정"
                initProductData={productData}/>
    )
}
export default ProductUpdate;