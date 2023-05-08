import ApiCommon from "@/api/common/ApiCommon";
import {memberRequest} from "@/api/common/Url";
const MemberApi={
    signUp: async (member:SignUpFormData)=>{
        const {data} = await ApiCommon.basicAPI.post(memberRequest.signUp,member);
        return data;
    },
    signIn : async (loginRequest:SignInFormData)=>{
        console.log(loginRequest)
        const {data}=await ApiCommon.testAPI.post(memberRequest.signIn,loginRequest);
        return data;
    }

}

export default MemberApi;