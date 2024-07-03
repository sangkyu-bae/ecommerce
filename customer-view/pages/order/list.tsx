import React, {useEffect} from 'react';
import GridComponent, {
    StyledContainer,
    StyledContent,
    StyledOrderBox,
    StyledSetion
} from "@/components/common/GridComponent";
import useCustomQuery from "@/shared/hook/useCustomQuery";
import {OrderApi} from "@/shared/api/order/orderApi";
import styles from '../../styles/orderList.module.css'
import OrderInfoBox from "@/components/order/OrderInfoBox";
import {BasketAPi} from "@/shared/api/basket/BasketAPi";
function List(props) {

    const {data} = useCustomQuery({
        submit:null,
        queryKey:'order-list',
        select: OrderApi.read(),
        refetch:true,
        update:null
    })

    useEffect(()=>{
        console.log(data)
    },[data])

    return (
        <StyledContainer>
            <StyledContent isFull={true}>
                <StyledSetion isFull={true}>
                    <div className="first-section">
                        <GridComponent title={`주문 현황`}>
                            <StyledOrderBox>
                                <div className="main-box">
                                    <div  className="main-box-first main-box-element-right">
                                        <div className="center">상품정보</div>
                                    </div>
                                    <div className="main-box-element-right main-box-remain">
                                        <div className="center">수량</div>
                                    </div>
                                    <div className="main-box-element-right main-box-remain">
                                        <div className="center">주문금액</div>
                                    </div>
                                    <div className="main-box-remain">
                                        <div className="center">주문현황</div>
                                    </div>
                                </div>

                                {
                                    data &&
                                    data.map((obj) => <OrderInfoBox
                                        key={obj.id}
                                        title={obj.productName}
                                        colorName={obj.colorName}
                                        sizeName = {obj.size}
                                        quantity= {obj.amount}
                                        price = {obj.payment}
                                        >
                                            <div className="main-box-remain">
                                                <div className="center">{obj.statusCode.name}</div>
                                            </div>
                                        </OrderInfoBox>
                                    )
                                }

                            </StyledOrderBox>
                        </GridComponent>
                    </div>
                </StyledSetion>
            </StyledContent>
        </StyledContainer>
    );
}

export default List;
