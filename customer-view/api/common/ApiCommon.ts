import axios from "axios";

const ApiCommon={
    basicAPI:axios.create({
        baseURL:"/"
    })
}

export default ApiCommon;