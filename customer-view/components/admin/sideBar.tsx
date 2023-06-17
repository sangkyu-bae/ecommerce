import React from 'react';
import Divider from '@mui/material/Divider';
import Paper from '@mui/material/Paper';
import MenuList from '@mui/material/MenuList';
import MenuItem from '@mui/material/MenuItem';
import ListItemText from '@mui/material/ListItemText';
import ListItemIcon from '@mui/material/ListItemIcon';
import Typography from '@mui/material/Typography';
import ContentCut from '@mui/icons-material/ContentCut';
import ContentCopy from '@mui/icons-material/ContentCopy';
import ContentPaste from '@mui/icons-material/ContentPaste';
import Cloud from '@mui/icons-material/Cloud';
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
    const StyledSideBar = styled.div`
        flex : 0.13;
        background-color : #434343;
        height : 100%
      
    `
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
        // <StyledContainer>
        //     <StyledSideBar>
        //         {menuList()}
        //     </StyledSideBar>
        // </StyledContainer>
        <StyledSideBar>
            {menuList()}
        </StyledSideBar>

        // <Paper sx={{ width: 320, maxWidth: '100%' }}>
        //     <MenuList>
        //         <MenuItem>
        //             <ListItemIcon>
        //                 <ContentCut fontSize="small" />
        //             </ListItemIcon>
        //             <ListItemText>Cut</ListItemText>
        //             <Typography variant="body2" color="text.secondary">
        //                 ⌘X
        //             </Typography>
        //         </MenuItem>
        //         <MenuItem>
        //             <ListItemIcon>
        //                 <ContentCopy fontSize="small" />
        //             </ListItemIcon>
        //             <ListItemText>Copy</ListItemText>
        //             <Typography variant="body2" color="text.secondary">
        //                 ⌘C
        //             </Typography>
        //         </MenuItem>
        //         <MenuItem>
        //             <ListItemIcon>
        //                 <ContentPaste fontSize="small" />
        //             </ListItemIcon>
        //             <ListItemText>Paste</ListItemText>
        //             <Typography variant="body2" color="text.secondary">
        //                 ⌘V
        //             </Typography>
        //         </MenuItem>
        //         <Divider />
        //         <MenuItem>
        //             <ListItemIcon>
        //                 <Cloud fontSize="small" />
        //             </ListItemIcon>
        //             <ListItemText>Web Clipboard</ListItemText>
        //         </MenuItem>
        //     </MenuList>
        // </Paper>
    );
}

export default SideBar;