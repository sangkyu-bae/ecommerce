import {MyProduct, OrderProduct} from "@/store/product/myProduct";

const SET_PRODUCT ="SET_PRODUCT";
const INIT_PRODUCT ="INIT_PRODUCT";
const INCREASE_QUANTITY = "INCREASE_QUANTITY";
const DECREASE_QUANTITY = "DECREASE_QUANTITY";
const REMOVE_BUY_PRODUCT = "REMOVE_BUY_PRODUCT";
const ADD_BUY_PRODUCT = "ADD_BUY_PRODUCT";

export const setProduct = (orderProduct:OrderProduct) =>{
    return{
        type : SET_PRODUCT,
        product : orderProduct
    }
}
export const addBuyProduct = (colorId, sizeId) =>{
    return {
        type : ADD_BUY_PRODUCT,
        command : {
            colorId : colorId,
            sizeId : sizeId
        }
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
const initialState : OrderProduct ={
    isOrderData : false,
    totalPayment : 0,
    product: {},
    selectProducts:[]
};

const productRedux = (state = initialState,action) =>{
    switch (action.type){
        case SET_PRODUCT:{
            // return [...state, action.product];
            return {
                ...state,
                product : action.product
            };
        }
        case ADD_BUY_PRODUCT:{
            const totalPay = state.totalPayment + state.product.price;
            const selectProduct = {
                colorId : action.command.colorId,
                sizeId : action.command.sizeId,
                quantity : 1
            };

            return {
                ...state,
                totalPayment : totalPay,
                selectProducts : [...state.selectProducts, selectProduct]
            }
        }
        case INIT_PRODUCT:{
            return initialState
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