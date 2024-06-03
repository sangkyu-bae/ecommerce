import React from 'react';
import GridComponent, {
    StyledContainer,
    StyledContent,
    StyledSetion
} from "@/components/common/GridComponent";

function Basket(props) {
    return (
        <StyledContainer>
            <StyledContent isFull={true}>
                <StyledSetion isFull={true}>
                    <div className="first-section">
                        <GridComponent title={`장바구니`}>
               
                        </GridComponent>
                    </div>
                </StyledSetion>
            </StyledContent>
        </StyledContainer>
    );
}

export default Basket;
