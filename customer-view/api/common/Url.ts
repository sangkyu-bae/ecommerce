const basicUrl : string ="http://localhost:8080";
const requestURL : string = `${basicUrl}/api`
const memberURL : string = `${requestURL}/member`
type MemberRequest={
    signUp:string;
    signIn:string;
}
export const memberRequest : MemberRequest={
    signUp:`${memberURL}/sign-up`,
    signIn:`${basicUrl}/login`
}
