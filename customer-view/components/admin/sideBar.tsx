import React from 'react';
import styled, {css} from "styled-components";
import StoreIcon from '@mui/icons-material/Store';
import Link from "next/link";
import CommonUtil from "@/shared/utils/CommonUtil";

interface MenuData {
    menuName: string;
    url: string;
}

function SideBar({type}:string) {
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
        // font-size :${(props) => props.$isfirst ? '1.2em' : '0.7em'} ;
        font-size :${(props) => props.isSecond ? '0.7em' : '1.2em'} ;
        padding : 0.5em;
        color : #fffaf0;
        &:hover{  
          background-color : skyblue;
          color : blue;
          cursor: pointer;
        }
        display : ${(props) => props.$isfirst ? "block" : "none"};
       
    `
    const common = new CommonUtil();

    const menuList = () => {
        const menuList:MenuData[] = common.getMenuList(type);
        return menuList.map((menu, index) =>
            <Link key={index} href={menu.url} style={{textDecoration: "none"}}>
                <StyledMenu $isfirst={true}>{menu.menuName}</StyledMenu>
            </Link>
        );

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