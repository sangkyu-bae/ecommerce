import {useRouter} from "next/router";
import React, {useEffect, useState} from "react";
import {useQuery} from "@tanstack/react-query";
import {ProductApi} from "@/api/product/ProductApi";
import GridComponent, {StyledContainer, StyledContent, StyledSetion} from "@/components/common/GridComponent";
import ProductInfo from "@/components/admin/ProductInfo";
import {useDispatch, useSelector} from "react-redux";
import productRedux, {setProduct} from "@/store/product/productRedux";
import useProduct from "@/hook/useProduct";
import {MyProduct} from "@/store/product/myProduct";

function DetailUserProduct(){

    const router = useRouter()
    const {mainProductId}: number = router.query;
    const {isLoading,isError,error} = useProduct(mainProductId);
    const productRedux :MyProduct = useSelector(state => state.productRedux) ;
    // const [productData, setProductData] = useState<Product | undefined>(undefined);
    // const {data, isLoading, isError, error} = useQuery(
    //     ['productData'],
    //     () => ProductApi.readDetailProduct(mainProductId), {
    //         enabled: !!mainProductId,
    //         onSuccess: data => {
    //             console.log(data);
    //             setProductData(data);
    //             dispatch(setProduct(data))
    //         },
    //         onError: e => {
    //             console.log(e.message);
    //         }
    //     }
    // )
    //
    // const dispatch =useDispatch();
    //
    //
    // useEffect(()=>{
    //     console.log(productData);
    // },[productData])

    return (
        <StyledContainer>
            <StyledContent isFull={true}>
                <StyledSetion isFull={true}>
                    <div className="first-section">
                        {/*<GridComponent title={`📰${productData?.name}`}></GridComponent>*/}
                        {/*<div className="main-section">*/}
                        {/*    {*/}
                        {/*        productData != undefined && <ProductInfo severProductData={productData}></ProductInfo>*/}
                        {/*    }*/}
                        {/*</div>*/}

                        <GridComponent title={`📰${productRedux?.name}`}></GridComponent>
                        <div className="main-section">
                            {
                                productRedux != undefined && <ProductInfo severProductData={productRedux}></ProductInfo>
                            }
                        </div>
                    </div>
                </StyledSetion>
            </StyledContent>
        </StyledContainer>
    )

}

export default DetailUserProduct;