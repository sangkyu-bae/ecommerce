import React from 'react';
import UploadProductComponent from "@/viewer/UploadProductComponent";
import {ProductApi} from "@/shared/api/product/ProductApi";

function Create(props) {
    const emptyProduct: Product = {
        id: 0,
        name: "",
        price: 0,
        productImage: "",
        description: "",
        brand: {},
        category: {},
        productComponents: []
    };


    const createApi = ProductApi.createProduct;
    return (
        <UploadProductComponent
            title="🛒상품 등록"
            buttonTitle="상품등록"
            initProductData={emptyProduct}
            submit={createApi}
            type='create'
        />
    );
}

export default Create;
