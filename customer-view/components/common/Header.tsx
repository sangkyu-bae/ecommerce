import React from 'react';
import { styled, ThemeProvider, createTheme } from "@mui/material/styles";
import { AppBar, Toolbar, Typography, Button, IconButton, Badge } from "@mui/material";
import Link from "next/link";
import LoginIcon from '@mui/icons-material/Login';
import LogoutIcon from '@mui/icons-material/Logout';
import ShoppingBasketIcon from '@mui/icons-material/ShoppingBasket';
import ManageAccountsIcon from '@mui/icons-material/ManageAccounts';
import Box from "@mui/material/Box";
import { useAuth } from "@/shared/hook/useAuth";
import { useRouter } from "next/router";
import MemberApi from "@/shared/api/MemberApi";
import { removeToken } from "@/shared/api/cookie/Cookie";

const theme = createTheme({
    palette: {
        primary: {
            main: '#2c3e50', // 더 세련된 다크블루 색상
        },
    },
});

const StyledAppBar = styled(AppBar)(({ theme }) => ({
    boxShadow: 'none',
    borderBottom: '1px solid rgba(255, 255, 255, 0.1)',
}));

const StyledToolbar = styled(Toolbar)({
    minHeight: '64px',
    display: 'flex',
    justifyContent: 'space-between',
});

const LogoText = styled(Typography)({
    fontWeight: 700,
    letterSpacing: '0.5px',
});

const NavLink = styled(Link)({
    textDecoration: 'none',
    color: 'inherit',
    marginLeft: '32px',
    transition: 'opacity 0.2s',
    '&:hover': {
        opacity: 0.8,
    },
});

const NavText = styled(Typography)({
    fontSize: '0.95rem',
    fontWeight: 500,
});

const IconsContainer = styled(Box)({
    display: 'flex',
    alignItems: 'center',
    gap: '20px',
});

const StyledIconButton = styled(IconButton)({
    color: 'white',
    '&:hover': {
        background: 'rgba(255, 255, 255, 0.1)',
    },
});

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
                        {!isLogin ? (
                            <NavLink href='/signIn'>
                                <StyledIconButton>
                                    <LoginIcon />
                                </StyledIconButton>
                            </NavLink>
                        ) : (
                            <>
                                <StyledIconButton onClick={handleLogout}>
                                    <LogoutIcon />
                                </StyledIconButton>
                                
                                <NavLink href='/basket'>
                                    <StyledIconButton>
                                        <Badge badgeContent={0} color="error">
                                            <ShoppingBasketIcon />
                                        </Badge>
                                    </StyledIconButton>
                                </NavLink>
                                
                                <NavLink href='/order/list'>
                                    <StyledIconButton>
                                        <ManageAccountsIcon />
                                    </StyledIconButton>
                                </NavLink>
                            </>
                        )}
                    </IconsContainer>
                </StyledToolbar>
            </StyledAppBar>
        </ThemeProvider>
    );
}

export default Header;