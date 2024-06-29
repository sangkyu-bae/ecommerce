import React from 'react';
import GridComponent, {
    StyledContainer,
    StyledContent,
    StyledOrderBox,
    StyledSetion
} from "@/components/common/GridComponent";
import useCustomQuery from "@/shared/hook/useCustomQuery";
import {OrderApi} from "@/shared/api/order/orderApi";

function List(props) {


    const {data} = useCustomQuery({
        submit:null,
        queryKey:'order-list',
        select: OrderApi.read(),
        refetch:false,
        update:null
    })

    return (
        <StyledContainer>
            <StyledContent isFull={true}>
                <StyledSetion isFull={true}>
                    <div className="first-section">
                        <GridComponent title={`주문 현황`}>
                            <StyledOrderBox>

                            </StyledOrderBox>
                        </GridComponent>
                    </div>
                </StyledSetion>
            </StyledContent>
        </StyledContainer>
    );
}

export default List;
