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
    return (
        <Box sx={{display:"flex"}}>
            <Box sx={{flex:'0.2'}}>
                {selectProduct.color.name}, {selectProduct.size.name}
            </Box>
            <Box sx={{flex:'0.2', display:"flex"}}>
                <Box onClick={()=>dispatch(increaseQuantity(productData.id,color.id,size.id))}>+</Box>
                <Box>
                    {selectProduct.quantity}
                </Box>
                <Box onClick={()=>dispatch(decreaseQuantity(productData.id,color.id,size.id))}>-</Box>
            </Box>

            <Box>
                {selectProduct.selectPrice} 원
            </Box>

            <Box onClick={()=>dispatch(removeBuyProduct(productData.id,color.id,size.id))}>
                X
            </Box>
        </Box>

    );
}

export default ProductOrderManagement;

