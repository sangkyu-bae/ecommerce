import React from 'react';
import GridComponent, {
    StyledContainer,
    StyledContent,
    StyledOrderBox,
    StyledSetion
} from "@/components/common/GridComponent";
import TableHeader from "@/components/common/TableHeader";
import OrderInfoBox from "@/components/order/OrderInfoBox";
import Box from "@mui/material/Box";
import Pagination from "@mui/material/Pagination";


interface MyOrderContainerProps {
    data: any;
    isLoading: boolean;
    totalPage: number;
    setPagingNm:void;
}

function MyOrderContainer({data,isLoading,totalPage,setPagingNm}) {
    const handleChange = (e,value) =>{
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
                                    <TableHeader type={'order'} />
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
                                                <div className="main-box-remain border-r">
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

export default MyOrderContainer;
