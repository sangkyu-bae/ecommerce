const basicUrl : string ="http://localhost:8000";
const userURL : string = `${basicUrl}/user`

type MemberRequest={
    signUp:string;
    signIn:string;
}
export const memberRequest : MemberRequest={
    signUp:`${userURL}/sign-up`,
    signIn:`${userURL}/login`
}
