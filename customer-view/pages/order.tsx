import React from 'react';
import {useDispatch, useSelector} from "react-redux";
import GridComponent, {StyledContainer, StyledContent, StyledSetion} from "@/components/common/GridComponent";
import usePayment from "@/shared/hook/usePayment";

function Order(props) {
    const dispatch = useDispatch();
    const orderProducts = useSelector(state => state.productRedux);
    const {payment,applyCoupon} = usePayment(null);

    return (
        <StyledContainer>
            <StyledContent isFull={true}>
                <StyledSetion isFull={true}>
                    <div className="first-section">
                        <GridComponent title={`주문하기`}></GridComponent>
                    </div>
                </StyledSetion>
                {
                    orderProducts.map(product => <div key={product.id}> {product.name}</div>)
                }
            </StyledContent>
        </StyledContainer>
    );
}

export default Order;