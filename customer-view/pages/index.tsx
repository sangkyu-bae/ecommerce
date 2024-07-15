import Image from 'next/image'
import { Inter } from 'next/font/google'
import Main from "@/viewer/Main";
import {useEffect} from "react";
import {useSelector} from "react-redux";

const inter = Inter({ subsets: ['latin'] })

export default function Home() {
    const {accessExpiredTime,accessToken,userName,isLogin} : Auth = useSelector(state => state.authRedux);

    console.log(isLogin)

  return (
     <Main>

     </Main>
  )
}
