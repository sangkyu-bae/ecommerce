import axios from "axios";
import {getAccessToken} from "@/api/cookie/Cookie";
const accessToken = getAccessToken()

const ApiCommon={
    basicAPI:axios.create({
        baseURL:"/",
        headers: {
            'Content-Type': 'application/json'
        }
    }),
    loginAPI:axios.create({
        baseURL:"/",
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded',
            Authorization:`${accessToken}`
        }
    }),
    testAPI:axios.create({
        baseURL:"/",
        headers: {
            'Content-Type': 'application/json',
            Authorization:`${accessToken}`
        }
    })
}

export default ApiCommon;