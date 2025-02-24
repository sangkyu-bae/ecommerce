import React, {useEffect} from 'react';
import {Container, StyledContainer} from "@/components/common/styles/GridComponent";
import MainSection from "@/components/main/MainSection";

function Main({data}) {

    return (
        <StyledContainer>
            <MainSection data={data}/>
        </StyledContainer>
    );
}

export default Main;