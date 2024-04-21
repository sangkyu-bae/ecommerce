import {combineReducers, createStore} from "redux";
import productRedux from "@/store/product/productRedux";

const rootReducer =combineReducers({
    productRedux:productRedux,
});
const store =createStore(rootReducer);

export default rootReducer;