import React from 'react';
import Box from "@mui/material/Box";
import IconButton from "@mui/material/IconButton";
import AddIcon from "@mui/icons-material/Add";
import RemoveIcon from "@mui/icons-material/Remove";
import CloseIcon from "@mui/icons-material/Close";
import styled from "styled-components";
import { Typography } from "@mui/material";
import {selectProduct} from "@/store/product/myProduct";
import {useDispatch, useSelector} from "react-redux";
import {decreaseQuantity, increaseQuantity, removeBuyProduct} from "@/store/product/productRedux";
import {useProductValueContext} from "@/components/product/ProductInfo";

const OrderContainer = styled(Box)`
  display: flex;
  align-items: center;
  padding: 12px 15px;
  margin: 8px 0;
  border-radius: 4px;
  background-color: #fff5e6;
  border: 1px solid #ffe8cc;
`;

const OptionInfo = styled(Box)`
  flex: 0.3;
  font-size: 14px;
  color: #495057;
`;

const QuantityControl = styled(Box)`
  flex: 0.2;
  display: flex;
  align-items: center;
  gap: 4px;
  margin: 0 15px;
`;

const QuantityButton = styled(IconButton)`
  padding: 4px;
  color: #1976d2;
  background-color: #fff;
  border: 1px solid #e9ecef;
  &:hover {
    background-color: #f8f9fa;
  }
`;

const QuantityText = styled(Typography)`
  min-width: 24px;
  text-align: center;
  font-weight: 500;
  color: #1a1a1a;
`;

const PriceInfo = styled(Box)`
  flex: 0.3;
  font-weight: 500;
  color: #1a1a1a;
  text-align: right;
  padding-right: 20px;
`;

const DeleteButton = styled(IconButton)`
  color: #adb5bd;
  padding: 4px;
  &:hover {
    color: #495057;
    background-color: rgba(0, 0, 0, 0.04);
  }
`;

function ProductOrderManagement({selectProduct} : selectProduct) {
    const dispatch = useDispatch();
    const {color, size} = selectProduct;
    const {productData} = useProductValueContext();
    
    return (
        <OrderContainer>
            <OptionInfo>
                {color}, {size}
            </OptionInfo>
            
            <QuantityControl>
                <QuantityButton 
                    size="small"
                    onClick={() => dispatch(decreaseQuantity(productData.id, color, size))}
                >
                    <RemoveIcon fontSize="small" />
                </QuantityButton>
                
                <QuantityText>
                    {selectProduct.quantity}
                </QuantityText>
                
                <QuantityButton 
                    size="small"
                    onClick={() => dispatch(increaseQuantity(productData.id, color, size))}
                >
                    <AddIcon fontSize="small" />
                </QuantityButton>
            </QuantityControl>

            <PriceInfo>
                {(selectProduct.selectPrice * selectProduct.quantity).toLocaleString()}원
            </PriceInfo>

            <DeleteButton
                onClick={() => dispatch(removeBuyProduct(productData.id, color, size))}
            >
                <CloseIcon fontSize="small" />
            </DeleteButton>
        </OrderContainer>
    );
}

export default ProductOrderManagement;

