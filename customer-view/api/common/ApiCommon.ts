import axios from "axios";

const ApiCommon={
    basicAPI:axios.create({
        baseURL:"/"
    }),
    testAPI:axios.create({
        baseURL:"/",
        headers: {
            'Content-Type': 'application/json'
        }
    })
}

export default ApiCommon;