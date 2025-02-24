import React, {useEffect} from 'react';
import OrderInfoBox from "@/components/order/OrderInfoBox";
import {StyledOrderBox} from "@/components/common/styles/GridComponent";
import {useSelector} from "react-redux";
import {OrderProduct} from "@/store/product/myProduct";

function OrderInfoContainer(props) {
    const products :OrderProduct[] = useSelector(state => state.productRedux);
    return (
        <>
            <div className="main-box">
                <div className="main-box-first main-box-element-right">
                    <div className="center">상품정보</div>
                </div>
                <div className="main-box-element-right main-box-remain">
                    <div className="center">수량</div>
                </div>
                <div className="main-box-remain">
                    <div className="center">주문금액</div>
                </div>
            </div>
            {
                products.map((product, index) => <OrderInfoBox
                    key={index}
                    title={product.productName}
                    colorName={product.color}
                    sizeName={product.size}
                    quantity={product.quantity}
                    price={product.selectPrice * product.quantity}
                />)
            }
        </>

    );
}

export default OrderInfoContainer;

