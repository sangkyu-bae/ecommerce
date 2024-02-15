import React from 'react';
import {createTheme, ThemeProvider} from "@mui/material/styles";
import {AppBar, Toolbar, Typography} from "@mui/material";
import Link from "next/link";
import LoginIcon from '@mui/icons-material/Login';
import Box from "@mui/material/Box";

const theme = createTheme({
    palette: {
        primary: {
            main: '#000000', // 검정색
        },
    },
});

function Header(props) {
    return (
        <ThemeProvider theme={theme}>
            <AppBar position="static">
                <Toolbar>
                    <Box sx={{flex:'0.9', display:'flex'}} >
                        <Link href='/' style={{
                            textDecoration: 'none',
                            color: 'inherit',
                        }}>
                            <Typography variant="h6" component="div">
                                My App
                            </Typography>
                        </Link>
                    </Box>


                    <Link href='/signIn'>
                        <LoginIcon style={{
                            textDecoration: 'none',
                            color: 'white',
                        }}></LoginIcon>
                    </Link>

                </Toolbar>
            </AppBar>
        </ThemeProvider>
    );
}

export default Header;