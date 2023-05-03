const requestURL : string = "http://localhost:8080/api"
const memberURL : string = `${requestURL}/member`
type MemberRequest={
    signUp:string;
}
export const memberRequest : MemberRequest={
    signUp:`${memberURL}/sign-up`
}
