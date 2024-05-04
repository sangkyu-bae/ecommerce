import React from 'react';
import Box from "@mui/material/Box";
import {useSelector} from "react-redux";

function ProductTotalPay(props) {
    const {totalPayment} = useSelector(state => state.productRedux)
    return (
        <Box sx={{display:"flex"}}>
            <Box sx ={{flex:'0.8'}}>총 상품 금액</Box>
            <Box>{totalPayment} 원</Box>
        </Box>
    );
}

export default ProductTotalPay;