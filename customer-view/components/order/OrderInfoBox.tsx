import React from 'react';
import {MyProduct, selectProduct} from "@/store/product/myProduct";
import Box from "@mui/material/Box";

interface Props{
    title: string;
    selectProduct: selectProduct
}
function OrderInfoBox({title,selectProduct }:Props) {
    console.log(selectProduct)
    return (
        <div className="sub-box">
            <div className="main-box-first main-box-element-right">
                <div className="img-box"> 이미지</div>
                <div className="second-flex">
                    <div className="title-box">{title}</div>
                    <div className="sub-info-option-box">
                        옵션:{selectProduct.color.name} / {selectProduct.size.name}
                    </div>
                </div>
            </div>
            <div className="main-box-element-right main-box-remain">
                <div className="center">{selectProduct.quantity}</div>
            </div>
            <div className="main-box-remain">
                <div className="center">{selectProduct.selectPrice}</div>
            </div>
        </div>
    );
}

export default OrderInfoBox;