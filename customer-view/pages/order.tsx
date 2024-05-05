import React from 'react';
import {useDispatch, useSelector} from "react-redux";
import GridComponent, {
    StyledContainer,
    StyledContent,
    StyledOrderBox,
    StyledSetion
} from "@/components/common/GridComponent";

import OrderInfoBox from "@/components/order/OrderInfoBox";
import Button from "@mui/material/Button";

function Order(props) {
    const dispatch = useDispatch();
    const {product,selectProducts,isOrderData,totalPayment} = useSelector(state => state.productRedux);
    const productMap = new Map(product.map(item => [item.id, item]));
    const buyProduct = () =>{

    }

    return (
        <StyledContainer>
            <StyledContent isFull={true}>
                <StyledSetion isFull={true}>
                    <div className="first-section">
                        <GridComponent title={`주문하기`}>
                            <StyledOrderBox>
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
                                    selectProducts.map((obj,index) => <OrderInfoBox
                                        key={index}
                                        title={productMap.get(obj.productId).name}
                                        selectProduct={obj}
                                    />)
                                }
                            </StyledOrderBox>
                            <Button variant="contained" sx={{mt:2}}>{ totalPayment.toLocaleString('en-US')} 원 결제하기</Button>
                        </GridComponent>
                    </div>
                </StyledSetion>
            </StyledContent>
        </StyledContainer>
    );
}

export default Order;