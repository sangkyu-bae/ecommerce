// import '@/styles/globals.css'
import type {AppProps} from 'next/app'
import React from 'react';
import {createTheme, ThemeProvider} from "@mui/material/styles";
import {AppBar, Toolbar, Typography} from "@mui/material";
import Header from "@/components/common/Header";
import CssBaseline from "@mui/material/CssBaseline";
import Copyright from "@/components/common/Copyright";
import {RecoilRoot} from "recoil";
import { QueryClient, QueryClientProvider } from '@tanstack/react-query';
import { ReactQueryDevtools }from '@tanstack/react-query-devtools';
import {Provider as MyProvider} from 'react-redux';
import {configureStore} from "@reduxjs/toolkit";
const theme = createTheme({
    palette: {
        primary: {
            main: '#000000', // 검정색
        },
    },
});
// const queryClient = new QueryClient();
const queryClient = new QueryClient({
    defaultOptions: {
        queries: {
            refetchOnWindowFocus: false,
        },
    },
});

// const queryClient = new QueryClient({
//     defaultOptions: {
//         queries: {
//             networkMode: 'offlineFirst',
//         },
//         mutations: {
//             networkMode: 'offlineFirst',
//         },
//     },
// })
export default function App({Component, pageProps}: AppProps) {
    // const store = configureStore({reducer : myReducer} );
    return (
        <QueryClientProvider client={queryClient}>
            {/*<MyProvider store={store}>*/}
                <RecoilRoot>
                    <div>
                        <CssBaseline/>
                        <Header></Header>
                        <Component {...pageProps} />
                        <Copyright sx={{mt: 5}}/>
                    </div>
                </RecoilRoot>
            {/*</MyProvider>*/}

        </QueryClientProvider>
    )
}
