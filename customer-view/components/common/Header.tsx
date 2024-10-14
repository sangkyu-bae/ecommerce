import React, {useEffect} from 'react';
import {createTheme, ThemeProvider} from "@mui/material/styles";
import {AppBar, Toolbar, Typography} from "@mui/material";
import Link from "next/link";
import LoginIcon from '@mui/icons-material/Login';
import Box from "@mui/material/Box";
import {getAccessToken, removeToken} from "@/shared/api/cookie/Cookie";
import LogoutIcon from '@mui/icons-material/Logout';
import MemberApi from "@/shared/api/MemberApi";
import {useAuth} from "@/shared/hook/useAuth";
import ShoppingBasketIcon from '@mui/icons-material/ShoppingBasket';
import {useRouter} from "next/router";
import ManageAccountsIcon from '@mui/icons-material/ManageAccounts';
const theme = createTheme({
    palette: {
        primary: {
            main: '#000000', // 검정색
        },
    },
});


let accessToken = getAccessToken();
function Header(props) {
    const {isLogin,onLogout} = useAuth();
    const router = useRouter();
    const handleLogout=  () =>{
        try{
            const logOutRes = MemberApi.logOut();
            removeToken('ACCESS_TOKEN');
            onLogout();
            router.push("/")
        }catch (e){
            alert("logout 에러 발생했습니다");
            console.log(e)
        }
    }
    return (
        <ThemeProvider theme={theme}>
            <AppBar position="static">
                <Toolbar>
                    <Box sx={{flex:'0.9', display:'flex'}} >
                        <Link href='/' style={{
                            textDecoration: 'none',
                            color: 'inherit',
                            flex:'0.1'
                        }}>
                            <Typography variant="h6" component="div">
                                My App
                            </Typography>
                        </Link>
                        <Link href='/product/list' style={{
                            textDecoration: 'none',
                            color: 'inherit',
                            flex:'0.1'
                        }}>
                            <Typography variant="h6" component="div">
                                상품목록
                            </Typography>
                        </Link>
                    </Box>

                    {
                        !isLogin ?
                            <Link href='/signIn'>
                                <LoginIcon style={{
                                    textDecoration: 'none',
                                    color: 'white',
                                }}></LoginIcon>
                            </Link> :
                            <>
                                <div onClick={handleLogout} style={{ cursor: 'pointer' ,marginRight:'1em'}}>
                                    <LogoutIcon style={{
                                        textDecoration: 'none',
                                        color: 'white',
                                    }} />
                                </div>
                                <Link href='/basket' style={{marginRight:'1em'}}>
                                    <ShoppingBasketIcon style={{
                                        textDecoration: 'none',
                                        color: 'white',
                                    }}></ShoppingBasketIcon>
                                </Link>
                                <Link href='/order/list'>
                                    <ManageAccountsIcon style={{
                                        textDecoration: 'none',
                                        color: 'white',
                                    }}></ManageAccountsIcon>
                                </Link>
                            </>
                    }



                </Toolbar>
            </AppBar>
        </ThemeProvider>
    );
}

export default Header;