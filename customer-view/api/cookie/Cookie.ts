
import {Cookies, useCookies} from 'react-cookie';

const cookies=new Cookies();

export const setToken = (key: 'ACCESS_TOKEN' | 'REFRESH_TOKEN' , token:string, expiredTime:string) =>{
    const expires =new Date(expiredTime);
    console.log(expires);
    cookies.set(key, token, {
        path:'/',
        expires :expires
    })
}

const removeToken = (key: 'ACCESS_TOKEN' | 'REFRESH_TOKEN')=>{
    cookies.remove(key,{path:'/'});
}






