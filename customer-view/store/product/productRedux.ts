import {MyProduct, OrderProduct} from "@/store/product/myProduct";

const SET_PRODUCT ="SET_PRODUCT";
const INIT_PRODUCT ="INIT_PRODUCT";
const INCREASE_QUANTITY = "INCREASE_QUANTITY";
const DECREASE_QUANTITY = "DECREASE_QUANTITY";
const REMOVE_BUY_PRODUCT = "REMOVE_BUY_PRODUCT";

export const setProduct = (orderProduct:OrderProduct) =>{
    return{
        type : SET_PRODUCT,
        product : orderProduct
    }
}
export const initProduct = () =>{
    return {
        type: INIT_PRODUCT
    }
}
export const increaseQuantity = (productId : number, sizeId : number) => {
    return {
        type : INCREASE_QUANTITY,
        command : {
            productId:productId,
            sizeId : sizeId
        }
    }
}
export const decreaseQuantity = (productId : number, sizeId : number) => {
    return {
        type : DECREASE_QUANTITY,
        command : {
            productId:productId,
            sizeId : sizeId
        }
    }
}
export const removeBuyProduct = (productId : number) => {
    return {
        type : REMOVE_BUY_PRODUCT,
        command : {
            productId:productId
        }
    }
}
const initialState : OrderProduct[] = [];

const productRedux = (state = initialState,action) =>{
    switch (action.type){
        case SET_PRODUCT:{
            return [...state, action.product];
        }
        case INIT_PRODUCT:{
            return []
        }
        case INCREASE_QUANTITY:{
            return
        }
        case DECREASE_QUANTITY:{
            return
        }
        case REMOVE_BUY_PRODUCT:{

        }
        default:
            return state
    }
}

export default productRedux;