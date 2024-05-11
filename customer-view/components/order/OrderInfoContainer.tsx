import React from 'react';
import OrderInfoBox from "@/components/order/OrderInfoBox";
import {StyledOrderBox} from "@/components/common/GridComponent";
import {useSelector} from "react-redux";

function OrderInfoContainer(props) {
    const {product, selectProducts, isOrderData, totalPayment} = useSelector(state => state.productRedux);
    const productMap = new Map(product.map(item => [item.id, item]));
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
                selectProducts.map((obj, index) => <OrderInfoBox
                    key={index}
                    title={productMap.get(obj.productId).name}
                    selectProduct={obj}
                />)
            }
        </>

    );
}

export default OrderInfoContainer;

