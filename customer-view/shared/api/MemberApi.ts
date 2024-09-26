import ApiCommon from "@/shared/api/common/ApiCommon";
import {memberRequest} from "@/shared/constants/Url";
const MemberApi={
    signUp: async (member:SignUpFormData)=>{
        const {data} = await ApiCommon.basicAPI.post(memberRequest.signUp,member);
        return data;
    },
    signIn : async (loginRequest:SignInFormData)=>{
        const {data}=await ApiCommon.loginAPI.post(memberRequest.signIn,{
            "email":loginRequest.email,
            "password":loginRequest.password
        });
        return data;
    },
    logOut :async () =>{
        const {data} = await ApiCommon.loginAPI.post(memberRequest.logOut);
        return data;
    }
}

export default MemberApi;