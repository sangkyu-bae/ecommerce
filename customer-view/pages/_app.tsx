import type { AppProps } from 'next/app';
import React, {  Suspense } from 'react';
import { createTheme, ThemeProvider } from "@mui/material/styles";
import { CssBaseline } from "@mui/material";
import Header from "@/components/common/Header";
import Copyright from "@/components/common/Copyright";
import { RecoilRoot } from "recoil";
import { QueryClient, QueryClientProvider } from '@tanstack/react-query';
import { ReactQueryDevtools } from '@tanstack/react-query-devtools';
import { Provider as ReduxProvider } from 'react-redux';
import { PersistGate } from "redux-persist/integration/react";
import {persistor, store} from "@/store/configStroe";
import {CookiesProvider} from "react-cookie";
import Loading from "@/components/common/Loading";


const queryClient = new QueryClient({
    defaultOptions: {
        queries: {
            suspense: true,
            refetchOnWindowFocus: false,
        },
    },
});

export default function App({ Component, pageProps }: AppProps) {
    return (

            <QueryClientProvider client={queryClient}>
            <CookiesProvider>
            <ReduxProvider store={store}>
                <PersistGate loading={null} persistor={persistor}>
                    <RecoilRoot>
                        <CssBaseline />
                        <Header />
                        <Suspense fallback={<Loading/>}>
                            <Component {...pageProps} />
                        </Suspense>
                        <Copyright sx={{ mt: 5 }} />
                    </RecoilRoot>
                </PersistGate>
            </ReduxProvider>
            </CookiesProvider>
            <ReactQueryDevtools initialIsOpen={false} position='bottom-right' />
        </QueryClientProvider>
    );
}