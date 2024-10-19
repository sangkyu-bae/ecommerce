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

function DetailUserProduct() {
    const router = useRouter()
    const {mainProductId}: number = router.query;
    // const searchPage : string | null= router.query.searchPage;
    // const {data, isLoading, isError, error} = useProduct(mainProductId,searchPage);
    const {data,isLoading} = useProduct(mainProductId,"0");
    const products = useSelector(state => state.productRedux);
    const dispatch = useDispatch();
    const {isLogin} = useAuth();
    useEffect(() => {
        if (data) {
            dispatch(initProduct())
            console.log(data)
        }
    }, [data])

    useEffect(()=>{

        console.log(data)
    },[isLoading])


    const orderProduct = () => {
        router.push("/order");
    }

    const {updateMutation} = useBasket(false);

    const onClickBasket =()=>{
        const basketProducts = selectProducts.map(product => {
            console.log(product)
            return {
                productSizeId : product.size.id,
                quantity : product.quantity,
                productId : product.productId,
                size: product.size.name
            }
        });
        updateMutation.mutate(basketProducts)
    }
    return (
        <>
            {
                !isLoading && data &&
                <ProductInfo productData={data}>
                    <div className="flex">
                        <ProductInfo.ProductImage/>
                        <div className="section">
                            <div>
                                <span className="bold"><span>Product Info</span> <span
                                    className="gray">제품정보</span></span>
                            </div>
                            <ProductInfo.ProductBrand/>
                            <ProductInfo.ProductPrice/>
                            <ProductInfo.ProductSizeQuantity/>
                            {
                                products.length > 0 &&
                                products.map(product => <ProductInfo.ProductOrderManagement
                                        key={product.color.id}
                                        selectProduct={product}
                                    />)
                            }
                            <ProductInfo.ProductTotalPay></ProductInfo.ProductTotalPay>
                            {
                                isLogin &&
                                <Box sx={{mt: 3}}>
                                    <Button variant="contained" sx={{mr: 2}} onClick={orderProduct}>구매하기</Button>
                                    <Button variant="outlined" onClick ={onClickBasket}>장바구니</Button>
                                </Box>
                            }

                        </div>
                    </div>
                    <ProductInfo.ProductDescription/>
                </ProductInfo>
            }
        </>
    )

}

export default DetailUserProduct;