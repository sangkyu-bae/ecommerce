const basicUrl : string ="http://localhost:8000";
const userURL : string = `${basicUrl}/user`
const productURL : string = `${basicUrl}/admin`

type MemberRequest={
    signUp:string;
    signIn:string;
}

type ProductRequest={ createProduct : string,
    readAllBrand : string,
    readAllCategory :string,
    readAllColor : string,
    readAllSize : string,
    readProduct : string
}
export const memberRequest : MemberRequest={
    signUp:`${userURL}/sign-up`,
    signIn:`${userURL}/login`
}

export const productRequest : ProductRequest={
    createProduct : `${productURL}/product`,
    readAllBrand : `${productURL}/brands`,
    readAllCategory : `${productURL}/categorys`,
    readAllColor : `${productURL}/colors`,
    readAllSize : `${productURL}/sizes`,
    readProduct :`${productURL}`
}
