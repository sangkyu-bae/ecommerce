import {MyProduct} from "@/store/product/myProduct";

const SET_PRODUCT ="SET_PRODUCT";

export const setProduct = (productInfo:MyProduct) =>{
    return{
        type : SET_PRODUCT,
        product:productInfo
    }
}
productRedux
const initialState : MyProduct ={
    id: 0,
    aggregateIdentifier: "",
    name: "",
    price: 0,
    productImage: "",
    description: "",
    brand: {
        id: 0,
        name: ""
    },
    category: {
        id: 0,
        name: ""
    },
    productComponents: []
};

const productRedux = (state = initialState,action) =>{
    switch (action.type){
        case SET_PRODUCT:{
            return {
                ...state,
                ...action.product,
            }
        }
        default:
            return state
    }
}

export default productRedux;