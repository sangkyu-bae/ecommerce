// import '@/styles/globals.css'
import type {AppProps} from 'next/app'
import React from 'react';
import {createTheme, ThemeProvider} from "@mui/material/styles";
import {AppBar, Toolbar, Typography} from "@mui/material";
import Header from "@/components/common/Header";
import CssBaseline from "@mui/material/CssBaseline";
import { QueryClient, QueryClientProvider, useQuery } from 'react-query'
const theme = createTheme({
    palette: {
        primary: {
            main: '#000000', // 검정색
        },
    },
});
const queryClient = new QueryClient();
export default function App({Component, pageProps}: AppProps) {
    return (
        <QueryClientProvider client={queryClient}>
            <div>
                <CssBaseline/>
                <Header></Header>
                <Component {...pageProps} />
            </div>
        </QueryClientProvider>
    )
}
