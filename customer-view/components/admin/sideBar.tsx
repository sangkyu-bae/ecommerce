import React from 'react';
import styled, {css} from "styled-components";
import StoreIcon from '@mui/icons-material/Store';

function SideBar(props) {
    const StyledButton = styled.button`
      padding: 6px 12px;
      border-radius: 8px;
      font-size: 1rem;
      line-height: 1.5;
      border: 1px solid lightgray;
    
      color: ${(props) => props.color || 'gray'};
      background: ${(props) => props.background || 'white'};
    
      ${(props) =>
            props.primary &&
            css`
        color: white;
        background: navy;
        border-color: navy;
      `}
    `;

    const StyledMenu = styled.div`
        font-size : 1.2em;
        padding : 0.5em;
        color : #fffaf0;
        &:hover{  
          background-color : skyblue;
          color : blue;
          cursor: pointer;
        }
    `

    const menuList = ()=>{
        const b :string[] =['상품관리','회원관리','test3']
        return b.map(a=><StyledMenu key={a}>{a}</StyledMenu>);
    }
    return (
        <StyledSideBar>
            {menuList()}
        </StyledSideBar>

    );
}
export const StyledSideBar = styled.div`
        flex : 0.13;
        background-color : #434343;
        height : 100%
      
    `
export default SideBar;