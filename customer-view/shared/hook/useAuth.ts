import {useDispatch, useSelector} from "react-redux";
import {login, logout} from "@/store/auth/authRedux";
export const useAuth = () =>{
    const dispatch = useDispatch();
    const {accessExpiredTime,accessToken,userName,isLogin} : Auth = useSelector(state => state.authRedux);


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

    const onLogout = () =>{
        dispatch(logout());
    }

    const getAccessToken = () =>{
        if (accessExpiredTime && new Date(accessExpiredTime) < new Date()) {
            // router.push('/signIn');
        }
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
        userName,
        isLogin
    }
}