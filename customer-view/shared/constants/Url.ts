const basicUrl : string ="http://localhost:8000";
const userURL : string = `${basicUrl}/user`
export const productURL : string = `${basicUrl}/admin`
const rankURL : string =`${basicUrl}/rank`;
const basketURL : string = `${basicUrl}/basket`;
const orderURL : string = `${basicUrl}/order`
type MemberRequest={
    signUp:string;
    signIn:string;
}

type ProductRequest={
    createProduct : string,
    readAllBrand : string,
    readAllCategory :string,
    readAllColor : string,
    readAllSize : string,
    readProduct : string,
}

type RankRequest ={
    readProductRank : string
}

type BasketRequest ={
    createBasket : string,
    readBasket: string
}

type OrderRequest = {
    register : string
}
export const memberRequest : MemberRequest={
    signUp:`${userURL}/sign-up`,
    signIn:`${userURL}/login`
}

export const productRequest : ProductRequest={
    createProduct : `${productURL}/register/product`,
    readAllBrand : `${productURL}/brands`,
    readAllCategory : `${productURL}/categorys`,
    readAllColor : `${productURL}/colors`,
    readAllSize : `${productURL}/sizes`,
    readProduct :`${productURL}/page/product`,
}

export const rankRequest : RankRequest={
    readProductRank : `${rankURL}/click/6`
}


export const basketRequest : BasketRequest ={
    createBasket : `${basketURL}/mulity`,
    readBasket:`${basketURL}`
}
export const orderRequest : OrderRequest = {
    register : `${orderURL}/register`
}