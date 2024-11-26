// import {useRouter} from "next/router";
// import React, {createContext, useEffect} from "react";
// import ProductInfo from "@/components/product/ProductInfo";
// import useProduct from "@/shared/hook/useProduct";
// import Button from '@mui/material/Button';
// import Box from "@mui/material/Box";
// import {useDispatch, useSelector} from "react-redux";
// import productRedux, {initProduct, setProduct} from "@/store/product/productRedux";
// import useCustomQuery from "@/shared/hook/useCustomQuery";
// import {BasketAPi} from "@/shared/api/basket/BasketAPi";
// import {useBasket} from "@/shared/hook/useBasket";
// import {useAuth} from "@/shared/hook/useAuth";
// import {OrderProduct} from "@/store/product/myProduct";
// import {useMutation, UseMutationOptions} from "@tanstack/react-query";
// import Loading from "@/components/common/Loading";
// import {ErrorBoundary} from "@/components/error/ErrorBoundary";
//
// function DetailUserProduct() {
//     const router = useRouter()
//     const {mainProductId}: number = router.query;
//
//     const {data, isLoading,error} = useProduct(mainProductId);
//     const products = useSelector(state => state.productRedux);
//     const dispatch = useDispatch();
//     const {isLogin} = useAuth();
//
//     useEffect(() => {
//         if (data) {
//             dispatch(initProduct())
//         }
//     }, [data])
//
//     const orderProduct = () => {
//         router.push("/order");
//     }
//
//     const {submitMutation} = useBasket(false);
//     const onClickBasket = () => {
//         const basketProducts :OrderProduct[] = products.map(product => {
//             return {
//                 productSizeId: product.productSizeId,
//                 quantity: product.quantity,
//                 productId: product.productId,
//                 size: product.size,
//                 colorName : product.color
//             }
//         });
//         // updateMutation.mutate(basketProducts)
//         if(basketProducts.length < 1){
//             return;
//         }
//         submitMutation.mutate(basketProducts)
//     }
//     return (
//         <>
//             {
//                 isLoading && <Loading></Loading>
//             }
//             {
//                 // !isLoading && data &&
//                 !isLoading &&
//                     <ProductInfo productData={data} error={error}>
//                         <div className="flex">
//                             <ProductInfo.ProductImage/>
//                             <div className="section">
//                                 <div>
//                                 <span className="bold"><span>Product Info</span> <span
//                                     className="gray">제품정보</span></span>
//                                 </div>
//                                 <ProductInfo.ProductBrand/>
//                                 <ProductInfo.ProductPrice/>
//                                 <ProductInfo.ProductSizeQuantity/>
//                                 {
//                                     products.length > 0 &&
//                                     products.map(product => <ProductInfo.ProductOrderManagement
//                                         key={product.color.id}
//                                         selectProduct={product}
//                                     />)
//                                 }
//                                 <ProductInfo.ProductTotalPay></ProductInfo.ProductTotalPay>
//                                 {
//                                     isLogin &&
//                                     <Box sx={{mt: 3}}>
//                                         <Button variant="contained" sx={{mr: 2}} onClick={orderProduct}>구매하기</Button>
//                                         <Button variant="outlined" onClick={onClickBasket}>장바구니</Button>
//                                     </Box>
//                                 }
//
//                             </div>
//                         </div>
//                         <ProductInfo.ProductDescription/>
//                     </ProductInfo>
//
//             }
//         </>
//     )
//
// }
//
// export default DetailUserProduct;


import {useRouter} from "next/router";
import React, {createContext, useEffect} from "react";
import ProductInfo from "@/components/product/ProductInfo";
import useProduct from "@/shared/hook/useProduct";
import Button from '@mui/material/Button';
import Box from "@mui/material/Box";
import {useDispatch, useSelector} from "react-redux";
import productRedux, {initProduct, setProduct} from "@/store/product/productRedux";
import useCustomQuery from "@/shared/hook/useCustomQuery";
import {BasketAPi} from "@/shared/api/basket/BasketAPi";
import {useBasket} from "@/shared/hook/useBasket";
import {useAuth} from "@/shared/hook/useAuth";
import {OrderProduct} from "@/store/product/myProduct";
import {useMutation, UseMutationOptions} from "@tanstack/react-query";
import Loading from "@/components/common/Loading";
import {ErrorBoundary} from "@/components/error/ErrorBoundary";
import ProductFetcher from "@/components/product/ProductFetcher";
import ProductContainer from "@/components/product/ProductContainer";

function DetailUserProduct() {

    return (
        <ErrorBoundary>
            <ProductFetcher>
                <ProductContainer/>
            </ProductFetcher>
        </ErrorBoundary>
    )

}

export default DetailUserProduct;