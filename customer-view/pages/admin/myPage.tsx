import React from 'react';
import SideBar from "@/components/admin/sideBar";
import Containerss from "@/components/common/Containerss";
import styled from "styled-components";
import Content from "@/components/common/Content";


function MyPage(props) {
    const StyledContainer = styled.div`
        display : flex;
        width : 100%;
        height : 100vh;
    `

    return (
        <StyledContainer>
            <SideBar></SideBar>
            <Content>

            </Content>
        </StyledContainer>
    );
}

export default MyPage;