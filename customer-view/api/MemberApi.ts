import ApiCommon from "@/api/common/ApiCommon";
import {memberRequest} from "@/api/common/Url";
const MemberApi={
    signUp: async (member:SignUpFormData)=>{
        const {data} = await ApiCommon.basicAPI.post(memberRequest.signUp,member);
        return data;
    },

}

export default MemberApi;