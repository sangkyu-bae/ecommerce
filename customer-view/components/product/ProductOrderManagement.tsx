import React from 'react';
import Box from "@mui/material/Box";
import {selectProduct} from "@/store/product/myProduct";
import {useDispatch, useSelector} from "react-redux";
import {decreaseQuantity, increaseQuantity, removeBuyProduct} from "@/store/product/productRedux";
import {useProductValueContext} from "@/components/product/ProductInfo";

function ProductOrderManagement({selectProduct} : selectProduct) {
    const dispatch = useDispatch();
    const {color,size} = selectProduct;
    const {productData} = useProductValueContext();
    console.log(selectProduct);
    return (
        <Box sx={{display:"flex"}}>
            <Box sx={{flex:'0.2'}}>
                {selectProduct.color}, {selectProduct.size}
            </Box>
            <Box sx={{flex:'0.2', display:"flex"}}>
                <Box onClick={()=>dispatch(increaseQuantity(productData.id,color,size))}>+</Box>
                <Box>
                    {selectProduct.quantity}
                </Box>
                <Box onClick={()=>dispatch(decreaseQuantity(productData.id,color,size))}>-</Box>
            </Box>

            <Box>
                {selectProduct.selectPrice * selectProduct.quantity} 원
            </Box>

            <Box onClick={()=>dispatch(removeBuyProduct(productData.id,color,size))}>
                X
            </Box>
        </Box>

    );
}

export default ProductOrderManagement;

