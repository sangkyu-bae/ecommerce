import {OrderProduct} from "@/store/product/myProduct";

const SET_PRODUCT ="SET_PRODUCT";
const INIT_PRODUCT ="INIT_PRODUCT";
const INCREASE_QUANTITY = "INCREASE_QUANTITY";
const DECREASE_QUANTITY = "DECREASE_QUANTITY";
const REMOVE_BUY_PRODUCT = "REMOVE_BUY_PRODUCT";
const ADD_BUY_PRODUCT = "ADD_BUY_PRODUCT";
const BASKET_QUANTITY_SETTING = "BASKET_QUANTITY_SETTING";
const BASKET_ADD_BUY_PRODUCT ="BASKET_ADD_BUY_PRODUCT";
export const setProduct = (orderProducts:OrderProduct[]) =>{
    return{
        type : SET_PRODUCT,
        command :orderProducts
    }
}
export const addBuyProduct = (orderProduct:OrderProduct) =>{
    return {
        type : ADD_BUY_PRODUCT,
        command : orderProduct
    }
}

export const initProduct = () =>{
    return {
        type: INIT_PRODUCT
    }
}
export const increaseQuantity = (productId : number, color:string,size : number) => {
    return {
        type : INCREASE_QUANTITY,
        command : {
            productId:productId,
            color : color,
            size : size
        }
    }
}

export const decreaseQuantity = (productId : number, color:string,size : number) => {
    return {
        type : DECREASE_QUANTITY,
        command : {
            productId:productId,
            color : color,
            size : size
        }
    }
}
export const removeBuyProduct = (productId : number,color:string,size:number) => {
    return {
        type : REMOVE_BUY_PRODUCT,
        command : {
            productId:productId,
            color : color,
            size : size
        }
    }
}
const initialState : OrderProduct[] =[];

const productRedux = (state = initialState,action) =>{

    switch (action.type){

        case SET_PRODUCT:{
            return [...state, ...action.command];
        };
        case ADD_BUY_PRODUCT :{
            return [...state, action.command]
        }

        case REMOVE_BUY_PRODUCT:{
            const {productId,color,size} = action.command;

            const filterProduct = state.filter(order =>
                order.productId !== productId ||
                order.color !== color ||
                order.size !== size
            );

            return filterProduct;
        }

        case DECREASE_QUANTITY:{
            const {productId,color,size} = action.command;
            const updatedProducts = state.map(product => {
                if (product.productId === productId && product.color === color && product.size === size) {
                    return {
                        ...product,
                        quantity: product.quantity - 1 > 0 ? product.quantity - 1 : 1
                    };
                }
                return product;
            });

            return updatedProducts
        }

        case INCREASE_QUANTITY :{
            const {productId,color,size} = action.command;
            const updatedProducts = state.map(product => {
                if (product.productId === productId && product.color === color && product.size === size) {
                    return {
                        ...product,
                        quantity: product.quantity + 1
                    };
                }
                return product;
            });

            return updatedProducts
        }

        case INIT_PRODUCT :{
            return []
        }


        default:
            return state
    }
}


export default productRedux;