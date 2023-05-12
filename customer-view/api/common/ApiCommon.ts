import axios from "axios";

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
            'Content-Type': 'application/x-www-form-urlencoded'
        }
    })
}

export default ApiCommon;