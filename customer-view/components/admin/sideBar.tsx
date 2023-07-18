import React from 'react';
import styled, {css} from "styled-components";
import StoreIcon from '@mui/icons-material/Store';

interface MenuData{
    firstName : string;
    secondMenuNames : string[];
}
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
        // font-size :${(props) =>props.isFirst ? '1.2em': '0.7em'} ;
        font-size :${(props) =>props.isSecond ? '0.7em': '1.2em'} ;
        padding : 0.5em;
        color : #fffaf0;
        &:hover{  
          background-color : skyblue;
          color : blue;
          cursor: pointer;
        }
        display : ${(props) =>props.isFirst ? "block":"none"};
       
    `
    const menuList = () => {
        const menuDatas : MenuData[] = [
            {
                firstName:'상품관리',
                secondMenuNames:['상품등록', '상품확인']
            },
            {
                firstName:'회원관리',
                secondMenuNames:['회원로그','회원탈퇴']
            }
        ]
        const b : string[]=['상품관리','회원관리'];
        const d = menuDatas.map((menuData,index)=>{
            const dd:string = menuData.firstName;
            const g:string[] =menuData.secondMenuNames;
            const cc = g.map((dg,index)=><StyledMenu key={index} isFirst={true} isSecond={true}>{dg}</StyledMenu>);

            return <StyledMenu key={index} isFirst={true} isSecond={false}>
                {dd}
                {cc}
            </StyledMenu>
        })

        console.log(d)
        // return b.map(a => <StyledMenu key={a} isFirst={true}>{a}</StyledMenu>);
        return d;
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