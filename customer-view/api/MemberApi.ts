import ApiCommon from "@/api/common/ApiCommon";
import {memberRequest} from "@/constants/Url";
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
    }
}

export default MemberApi;