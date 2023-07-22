import React from 'react';
import styled from "styled-components";

interface InfoProps {
    title: string;
    children: React.ReactNode;
}

function GridComponent({title,children}: InfoProps) {
    return (
        <GridContainer>
            <h4>{title}</h4>
            <h5>{children}</h5>
        </GridContainer>
    );
}

const GridContainer = styled.div`
  padding: 10px 0px;
  text-align: center;
  h4 {
    padding: 5px 0px;
    font-size: 2em;
    font-weight: 600;
  }
  h5 {
    font-size: 1rem;
    font-weight: 400;
  }
  img {
    width: 100%;
    height: 100%;
    padding: 3px 0 3px 16px;
  }
`;
export const StyledContent = styled.div`
        flex : 0.87;
        background-color : #FFFAF0;
        display:flex;
    `
export const StyledSetion = styled.div`
    flex: ${(props) => props.$isproduct ? '1':'0.8'};
    // flex:0.8;
    .main-section{
        width : 80%;
        margin : 0 auto;
    }
    .btn-submit{
        float: right;
        margin-top: 2em;
    }
    .first-section{
        flex:1;
        width : 100%;
        margin : 0 auto;
    }
`
export const StyledMenu = styled.div`
    flex:0.2;
`
export const StyledContainer = styled.div`
        display : flex;
        width : 100%;
        height : 100vh;
    `
export default GridComponent;