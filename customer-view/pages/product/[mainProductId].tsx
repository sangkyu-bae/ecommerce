import {useRouter} from "next/router";
import React, {createContext, useEffect} from "react";
import ProductInfo from "@/components/product/ProductInfo";
import useProduct from "@/shared/hook/useProduct";
import Button from '@mui/material/Button';
import Box from "@mui/material/Box";
import {useBasket} from "@/shared/hook/useBasket";
import {useForm} from "react-hook-form";
import QuantityInput from "@/components/common/number/NumberInput";
function DetailUserProduct(){
    const router = useRouter()
    const {mainProductId}: number = router.query;
    const {data,isLoading,isError,error} = useProduct(mainProductId);
    // const onSubmit = (createBasket : CreateBasket) => {
    //     mutation.mutate(createBasket)
    // };
    // const {register, handleSubmit, setValue,formState: {errors} }=useForm<CreateBasket>();
    //
    // const {mutation} = useBasket();


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
                        <ProductInfo.ProductSizeQuantity/>
                        <Box sx={{mt:3}}>
                            <Button variant="contained" sx={{mr:2}}>구매하기</Button>
                            <Button variant="outlined" >장바구니</Button>
                        </Box>

                    </div>
                </div>
                <ProductInfo.ProductDescription/>
            </ProductInfo>
        }
        </>
    )

}

export default DetailUserProduct;