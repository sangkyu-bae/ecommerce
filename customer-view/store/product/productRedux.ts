import {MyProduct, OrderProduct} from "@/store/product/myProduct";

const SET_PRODUCT ="SET_PRODUCT";
const INIT_PRODUCT ="INIT_PRODUCT";
const INCREASE_QUANTITY = "INCREASE_QUANTITY";
const DECREASE_QUANTITY = "DECREASE_QUANTITY";
const REMOVE_BUY_PRODUCT = "REMOVE_BUY_PRODUCT";
const ADD_BUY_PRODUCT = "ADD_BUY_PRODUCT";
const BASKET_QUANTITY_SETTING = "BASKET_QUANTITY_SETTING";
const BASKET_ADD_BUY_PRODUCT ="BASKET_ADD_BUY_PRODUCT";
export const setProduct = (orderProduct:OrderProduct,isOrderData:boolean) =>{
    return{
        type : SET_PRODUCT,
        product : orderProduct,
        isOrderData : isOrderData
    }
}
export const addBuyProduct = (color:Data, size : Data,productId:number) =>{
    return {
        type : ADD_BUY_PRODUCT,
        command : {
            color : color,
            size : size,
            productId : productId
        }
    }
}

export const basketAddBuyProduct = (color:Data, size :Data ,productId : number) =>{
    return {
        type: BASKET_ADD_BUY_PRODUCT,
        command : {
            color:color,
            size: size,
            productId :productId
        }
    }
}
export const initProduct = () =>{
    return {
        type: INIT_PRODUCT
    }
}
export const increaseQuantity = (productId : number, colorId:number,sizeId : number) => {
    return {
        type : INCREASE_QUANTITY,
        command : {
            productId:productId,
            colorId : colorId,
            sizeId : sizeId
        }
    }
}

export const basketQuantitySetting = (productId : number, colorName:string, size: number, quantity :number)=>{
    return {
        type : BASKET_QUANTITY_SETTING,
        command :{
            productId : productId,
            colorName : colorName,
            size : size,
            quantity : quantity
        }
    }
}

export const decreaseQuantity = (productId : number, colorId:number,sizeId : number) => {
    return {
        type : DECREASE_QUANTITY,
        command : {
            productId:productId,
            colorId : colorId,
            sizeId : sizeId
        }
    }
}
export const removeBuyProduct = (productId : number,colorId:number,sizeId:number) => {
    return {
        type : REMOVE_BUY_PRODUCT,
        command : {
            productId:productId,
            colorId : colorId,
            sizeId : sizeId
        }
    }
}
const initialState : OrderProduct ={
    isOrderData : false,
    totalPayment : 0,
    product: [],
    selectProducts:[]
};

const productRedux = (state = initialState,action) =>{
    function selectData() {
        let selectProducts = [...state.selectProducts];

        let updateIndex = selectProducts
            .findIndex(selectProduct => selectProduct.color.id == action.command.colorId
                && selectProduct.size.id == action.command.sizeId);

        let updateSelectProduct = {...selectProducts[updateIndex]};
        const pay = state.product[0].price;
        return {
            selectProducts,
            updateIndex,
            updateSelectProduct,
            pay
        };
    }

    function selectBasketData(){
        let selectProduct = [...state.selectProducts];

        let updateIndex = selectProduct
            .findIndex(selectProduct => selectProduct.color.name == action.command.colorName
            && selectProduct.size.name == action.command.size);

        let updateSelectProduct = {...selectProduct[updateIndex]};

        let products = [...state.product];
        let getProductIndex = products.
            findIndex(product => product.productId == action.command.productId);

        let product = {...products[getProductIndex]}
        const pay = product.price

        return {
            selectProduct,
            updateIndex,
            updateSelectProduct,
            getProductIndex,
            product,
            pay
        }
    }

    switch (action.type){
        case SET_PRODUCT:{
            return {
                ...state,
                product : [
                    ...state.product,
                    action.product
                ],
                isOrderData:action.isOrderData
            };
        }
        case BASKET_ADD_BUY_PRODUCT :{
            const  {product} = selectBasketData();

            const selectProduct = {
                productId : action.command.productId,
                color : action.command.color,
                size : action.command.size,
                quantity : 1,
                selectPrice : product.price
            };

            return {
                ...state,
                totalPayment : 0,
                selectProducts : [...state.selectProducts, selectProduct],
                isOrderData:true
            }
        }
        case ADD_BUY_PRODUCT:{
            action.command.colorId = action.command.color.id;
            action.command.sizeId = action.command.size.id;

            const totalPay = state.totalPayment + state.product[0].price;
            const {updateSelectProduct}=selectData()

            if(Object.keys(updateSelectProduct).length != 0){
                return {
                    ...state
                }
            }
            const selectProduct = {
                productId : action.command.productId,
                color : action.command.color,
                size : action.command.size,
                quantity : 1,
                selectPrice : state.product[0].price
            };

            return {
                ...state,
                totalPayment : totalPay,
                selectProducts : [...state.selectProducts, selectProduct],
                isOrderData:true
            }
        }
        case INIT_PRODUCT:{
            return initialState
        }

        case BASKET_QUANTITY_SETTING:{
            const {pay,updateSelectProduct, selectProduct, updateIndex} = selectBasketData()
            updateSelectProduct.quantity = action.command.quantity

            console.log(pay)
            updateSelectProduct.selectPrice = pay * updateSelectProduct.quantity

            console.log(updateSelectProduct.selectPrice)
            selectProduct[updateIndex] = updateSelectProduct;
            const totalPayment = state.totalPayment + (pay * action.command.quantity);
            return {
                ...state,
                selectProducts : selectProduct,
                totalPayment : totalPayment
            }
        }

        case INCREASE_QUANTITY:{
            let {selectProducts, updateIndex, updateSelectProduct,pay} = selectData();

            updateSelectProduct.quantity += 1;

            updateSelectProduct.selectPrice = pay * updateSelectProduct.quantity;
            const totalPayment = state.totalPayment + pay;

            selectProducts[updateIndex] = updateSelectProduct
            return {
                ...state,
                selectProducts: selectProducts,
                totalPayment:totalPayment
            }
        }
        case DECREASE_QUANTITY:{
            let {selectProducts, updateIndex, updateSelectProduct,pay} = selectData();

            if(updateSelectProduct.quantity == 1){
                return {
                    ...state
                }
            }

            updateSelectProduct.quantity -= 1;

            updateSelectProduct.selectPrice = pay * updateSelectProduct.quantity;
            const totalPayment = state.totalPayment - pay;

            selectProducts[updateIndex] = updateSelectProduct
            return {
                ...state,
                selectProducts: selectProducts,
                totalPayment:totalPayment
            }
        }
        case REMOVE_BUY_PRODUCT:{
            let {selectProducts, updateIndex, updateSelectProduct,pay} = selectData();

            selectProducts = selectProducts.filter((product,index) => index != updateIndex);

            const totalPayment =  state.totalPayment - (pay * updateSelectProduct.quantity);
            return {
                ...state,
                selectProducts : selectProducts,
                totalPayment : totalPayment
            }

        }
        default:
            return state
    }
}


export default productRedux;