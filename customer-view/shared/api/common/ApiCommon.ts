import axios from "axios";
import { getAccessToken } from "@/shared/api/cookie/Cookie";
// let accessToken = getAccessToken()

const ApiCommon = {
    basicAPI: axios.create({
        baseURL: "/",
        headers: {
            'Content-Type': 'application/json'
        }
    }),
    loginAPI: axios.create({
        baseURL: "/",
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        }
    }),
    loginJsonAPI: axios.create({
        baseURL: "/",
        headers: {
            'Content-Type': 'application/json'
        }
    })
};

[ApiCommon.loginAPI, ApiCommon.loginJsonAPI].forEach(instance => {
    instance.interceptors.request.use(config => {
        const token = getAccessToken();
        if (token) {
            config.headers.Authorization = token;
        }
        return config;
    });
});

export default ApiCommon;