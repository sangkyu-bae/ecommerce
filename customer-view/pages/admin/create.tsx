import React from 'react';
import UploadProductComponent from "@/viewer/UploadProductComponent";

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
    return (
        <UploadProductComponent title="🛒상품 등록" buttonTitle="상품등록" initProductData={emptyProduct}/>
    );
}

export default Create;
