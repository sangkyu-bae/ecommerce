import React, {useEffect, useState} from 'react';
import GridComponent, {
    StyledContainer,
    StyledContent,
    StyledOrderBox,
    StyledSetion
} from "@/components/common/GridComponent";
import useCustomQuery from "@/shared/hook/useCustomQuery";
import {OrderApi} from "@/shared/api/order/orderApi";
import OrderInfoBox from "@/components/order/OrderInfoBox";
import {BasketAPi} from "@/shared/api/basket/BasketAPi";
import Box from "@mui/material/Box";
import Pagination from "@mui/material/Pagination";
import {useOrder} from "@/shared/hook/useOrder";


function List(props) {

    const [pagingNm,setPagingNm] = useState<number>(1);
    const {data,isLoading}= useOrder(pagingNm);
    const {pageNumber = 0 ,pageSize = 0 ,totalElement = 0,totalPage = 0} = data || {};

    const handleChange= (e,value) =>{
        setPagingNm(value);
    }
    return (
        <StyledContainer>
            <StyledContent isFull={true}>
                <StyledSetion isFull={true}>
                    <div className="first-section">
                        <GridComponent title={`주문 현황`}>
                            <StyledOrderBox>
                                <div className="herf-div">
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
                                        !isLoading &&
                                        data &&
                                        data.orderAggregationVos.map((obj) => <OrderInfoBox
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
                                </div>
                                <Box sx={{width:'28%', margin:'0 auto'}}>
                                    <Pagination count={totalPage}
                                                size="large"
                                                showFirstButton showLastButton
                                                onChange={handleChange}
                                    />
                                </Box>

                            </StyledOrderBox>
                        </GridComponent>
                    </div>
                </StyledSetion>
            </StyledContent>
        </StyledContainer>
    );
}

export default List;
