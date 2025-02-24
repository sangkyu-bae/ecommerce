import React from 'react';
import { ThemeProvider } from "@mui/material/styles";
import { Badge } from "@mui/material";
import LoginIcon from '@mui/icons-material/Login';
import LogoutIcon from '@mui/icons-material/Logout';
import ShoppingBasketIcon from '@mui/icons-material/ShoppingBasket';
import ManageAccountsIcon from '@mui/icons-material/ManageAccounts';
import Box from "@mui/material/Box";
import { useAuth } from "@/shared/hook/useAuth";
import { useRouter } from "next/router";
import MemberApi from "@/shared/api/MemberApi";
import { removeToken } from "@/shared/api/cookie/Cookie";
import {
    theme,
    StyledAppBar,
    StyledToolbar,
    LogoText,
    NavLink,
    NavText,
    IconsContainer,
} from './styles/HeaderStyles';
import HeaderFactory from './HeaderFactory';

function Header() {
    const {isLogin, onLogout} = useAuth();
    const router = useRouter();

    const handleLogout = async () => {
        try {
            await MemberApi.logOut();
            removeToken('ACCESS_TOKEN');
            onLogout();
            router.push("/");
        } catch (e) {
            console.error("로그아웃 중 오류가 발생했습니다:", e);
        }
    };

    return (
        <ThemeProvider theme={theme}>
            <StyledAppBar position="sticky">
                <StyledToolbar>
                    <Box display="flex" alignItems="center">
                        <NavLink href='/'>
                            <LogoText variant="h6">
                                SHOP
                            </LogoText>
                        </NavLink>
                        <NavLink href='/product/list'>
                            <NavText>상품목록</NavText>
                        </NavLink>
                    </Box>

                    <IconsContainer>
                        <HeaderFactory isLogin={isLogin} onLogout={handleLogout} />    
                    </IconsContainer>
                </StyledToolbar>
            </StyledAppBar>
        </ThemeProvider>
    );
}

export default Header;