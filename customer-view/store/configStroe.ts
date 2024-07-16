import { configureStore, combineReducers } from "@reduxjs/toolkit";
import productRedux from "@/store/product/productRedux";
import authRedux from "@/store/auth/authRedux";
import storage from 'redux-persist/lib/storage';
import { persistStore, persistReducer } from "redux-persist";

const persistConfig = {
    key: 'root',
    storage,
    blacklist: ['productRedux']
};

const rootReducer = combineReducers({
    productRedux: productRedux,
    authRedux: authRedux
});

const persistedReducer = persistReducer(persistConfig, rootReducer);

const store = configureStore({
    reducer: persistedReducer,
    middleware: getDefaultMiddleware => getDefaultMiddleware({ serializableCheck: false }),

    devTools: true,
});

const persistor = persistStore(store);

export { store, persistor };