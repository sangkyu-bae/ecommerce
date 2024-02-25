import {useRouter} from "next/router";
import React, {createContext, useEffect} from "react";
import ProductInfo from "@/components/product/ProductInfo";
import useProduct from "@/shared/hook/useProduct";

function DetailUserProduct(){
    const router = useRouter()
    const {mainProductId}: number = router.query;
    const {data,isLoading,isError,error} = useProduct(mainProductId);
    return (
        <>
        {
            !isLoading &&
            <ProductInfo productData={data}>
                <div className="flex">
                    <ProductInfo.ProductImage/>
                    <div className="section">
                        <div>
                            <span className="bold"><span>Product Info</span> <span className="gray">제품정보</span></span>
                        </div>
                        <ProductInfo.ProductBrand/>
                        <ProductInfo.ProductPrice/>
                    </div>
                </div>
                <ProductInfo.ProductDescription/>
            </ProductInfo>
        }
        </>
    )

}

export default DetailUserProduct;