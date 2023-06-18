const basicUrl : string ="http://localhost:8000";
const userURL : string = `${basicUrl}/user`
const productURL : string = `${basicUrl}/admin`

type MemberRequest={
    signUp:string;
    signIn:string;
}

type ProductRequest={
    createProduct : string
}
export const memberRequest : MemberRequest={
    signUp:`${userURL}/sign-up`,
    signIn:`${userURL}/login`
}

export const productRequest : ProductRequest={
    createProduct : `${productURL}/product`,
}
