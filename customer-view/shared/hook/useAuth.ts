import {useDispatch, useSelector} from "react-redux";
import {useEffect, useState} from "react";
import {login, logout} from "@/store/auth/authRedux";

export const useAuth = () =>{
    const dispatch = useDispatch();
    const {accessExpiredTime,accessToken,userName,isLogin} : Auth = useSelector(state => state.authRedux);

    useEffect(()=>{
        console.log(userName)
    },[userName])

    const onLogin = ( accessExpiredTime : string,
                    accessToken : string,
                    userName : string) =>{
        const loginData : Auth = {
            accessExpiredTime : accessExpiredTime,
            accessToken : accessToken,
            userName :userName,
            isLogin:true
        };

        dispatch(login(loginData));
    }

    const onLogout = () =>[
        dispatch(logout())
    ]

    const getAccessToken = () =>{
        return accessToken
    }

    const getUserName = () =>{
        return userName;
    }

    return {
        onLogin,
        onLogout,
        getAccessToken,
        getUserName,
        userName
    }
}