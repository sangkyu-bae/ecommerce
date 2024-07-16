import { Inter } from 'next/font/google'
import Main from "@/viewer/Main";
import {useAuth} from "@/shared/hook/useAuth";
import {useEffect} from "react";

const inter = Inter({ subsets: ['latin'] })

export default function Home() {
    const {onLogin,userName} = useAuth();
    useEffect(()=>{
        console.log(userName);
    },[userName])
  return (
     <Main>

     </Main>
  )
}
