import {MyProduct} from "@/store/product/myProduct";

const SET_PRODUCT ="SET_PRODUCT";
const INIT_PRODUCT ="INIT_PRODUCT";

export const setProduct = (productInfo:MyProduct) =>{
    return{
        type : SET_PRODUCT,
        product:productInfo
    }
}
export const initProduct = () =>{
    return {
        type: INIT_PRODUCT
    }

}
const initialState : MyProduct[] = [];

const productRedux = (state = initialState,action) =>{
    switch (action.type){
        case SET_PRODUCT:{
            return [...state, action.product];
        }
        case INIT_PRODUCT:{
            return []
        }
        default:
            return state
    }
}

export default productRedux;