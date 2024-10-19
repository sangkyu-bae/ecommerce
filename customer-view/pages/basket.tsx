import React, {useEffect} from 'react';
import GridComponent, {
    StyledContainer,
    StyledContent,
    StyledSetion
} from "@/components/common/GridComponent";
import Content from "@/components/basket/Content";
import Button from "@mui/material/Button";

function Basket(props) {

    return (
        <StyledContainer>
            <StyledContent isFull={true}>
                <StyledSetion isFull={true}>
                    <div className="first-section">
                        <GridComponent title={`장바구니`}>
                            <Content/>
                        </GridComponent>
                    </div>
                </StyledSetion>
            </StyledContent>
        </StyledContainer>
    );
}

export default Basket;
