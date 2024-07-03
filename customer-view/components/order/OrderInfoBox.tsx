import React, {useEffect} from 'react';
import {MyProduct, selectProduct} from "@/store/product/myProduct";
import Box from "@mui/material/Box";

interface Props{
    title: string;
    colorName: string,
    sizeName : string,
    quantity: number,
    price : number,
    children : React.ReactNode
}
// function OrderInfoBox({title,selectProduct }:Props) {
function OrderInfoBox({title,colorName,sizeName, quantity, price,children }:Props) {
    return (
        <div className="sub-box">
            <div className="main-box-first main-box-element-right">
                <div className="img-box"> 이미지</div>
                <div className="second-flex">
                    <div className="title-box">{title}</div>
                    <div className="sub-info-option-box">
                        {/*옵션:{selectProduct.color.name} / {selectProduct.size.name}*/}
                        옵션:{colorName} / {sizeName}
                    </div>
                </div>
            </div>
            <div className="main-box-element-right main-box-remain">
                <div className="center">{quantity}</div>
            </div>
            <div className="main-box-element-right  main-box-remain">
                {/*<div className="center">{selectProduct.selectPrice}</div>*/}
                <div className="center">{price}</div>
            </div>
            {
                children
            }
        </div>
    );
}

export default OrderInfoBox;