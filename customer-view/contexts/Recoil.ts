import {atom} from "recoil";

export const loginState= atom<LoginState>({
    key:'loginState',
    default:{
        token:null,
        expiredTime:null,
        isLogin:false
    }
})