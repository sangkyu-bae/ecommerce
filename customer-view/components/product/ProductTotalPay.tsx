import React from 'react';
import Box from "@mui/material/Box";
import {useSelector} from "react-redux";
import styled from "styled-components";
import { Typography } from "@mui/material";

const TotalContainer = styled(Box)`
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 20px 15px;
  margin: 16px 0;
  background-color: #fff5e6;
  border: 1px solid #ffe8cc;
  border-radius: 4px;
`;

const TotalLabel = styled(Typography)`
  font-size: 16px;
  font-weight: 600;
  color: #495057;
`;

const TotalAmount = styled(Typography)`
  font-size: 18px;
  font-weight: 700;
  color: #1a1a1a;
  
  .currency {
    font-size: 14px;
    font-weight: 500;
    margin-left: 4px;
    color: #868e96;
  }
`;

function ProductTotalPay() {
    const products = useSelector(state => state.productRedux);
    const totalPayment = products.reduce(
        (total, product) => total + product.quantity * product.selectPrice,
        0
    );

    return (
        <TotalContainer>
            <TotalLabel>총 상품 금액</TotalLabel>
            <TotalAmount>
                {totalPayment.toLocaleString()}
                <span className="currency">원</span>
            </TotalAmount>
        </TotalContainer>
    );
}

export default ProductTotalPay;