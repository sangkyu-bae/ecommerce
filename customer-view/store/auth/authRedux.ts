const LOGIN = "LOGIN";
const LOGOUT = "LOGOUT";

const initState: Auth = {
    accessExpiredTime: "",
    accessToken: "",
    userName: "",
    isLogin : false
};

export const login = (loginData : Auth) => {
    return {
        type: LOGIN,
        command: loginData
    }
}

export const logout = () =>{
    return {
        type : LOGOUT
    }
}

const authRedux = (state = initState, action) => {
    switch (action.type) {
        case LOGIN : {
            const { accessExpiredTime, accessToken, userName } = action.command;
            if(state.isLogin){
                return state
            }
            return {
                accessExpiredTime: accessExpiredTime,
                accessToken: accessToken,
                userName : userName,
                isLogin : true
            }
        }
        case LOGOUT: {
            return initState;
        }
        default :
            return state;
    }
}

export default authRedux;