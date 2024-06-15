import React, {useEffect} from 'react';
import GridComponent, {
    StyledContainer,
    StyledContent,
    StyledSetion
} from "@/components/common/GridComponent";
import {useBasket} from "@/shared/hook/useBasket";
import {useQuery} from "@tanstack/react-query";
import {BasketAPi} from "@/shared/api/basket/BasketAPi";
import useCustomQuery from "@/shared/hook/useCustomQuery";
import Content from "@/components/basket/Content";

function Basket(props) {

    const {data,isLoading,error} = useBasket(true, false);

    return (
        <StyledContainer>
            <StyledContent isFull={true}>
                <StyledSetion isFull={true}>
                    <div className="first-section">
                        <GridComponent title={`장바구니`}>
                            <Content></Content>
                        </GridComponent>
                    </div>
                </StyledSetion>
            </StyledContent>
        </StyledContainer>
    );
}

export default Basket;
