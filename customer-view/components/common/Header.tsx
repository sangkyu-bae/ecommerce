import React from 'react';
import {createTheme, ThemeProvider} from "@mui/material/styles";
import {AppBar, Toolbar, Typography} from "@mui/material";
import Link from "next/link";

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
                    <Link href='/' style={{
                        textDecoration: 'none',
                        color: 'inherit',
                    }}>
                        <Typography variant="h6" component="div">
                            My App
                        </Typography>
                    </Link>
                </Toolbar>
            </AppBar>
        </ThemeProvider>
    );
}

export default Header;