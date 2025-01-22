import { Inter } from 'next/font/google'
import Main from "@/viewer/Main";
import {useAuth} from "@/shared/hook/useAuth";
import {useEffect} from "react";
import {ErrorBoundary} from "@/components/error/ErrorBoundary";
import MainFetcher from "@/components/main/MainFetcher";

const inter = Inter({ subsets: ['latin'] })

export default function Home() {
  return (
      <ErrorBoundary>
          <MainFetcher>
              <Main/>
          </MainFetcher>
      </ErrorBoundary>

  )
}

