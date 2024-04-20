import React from 'react';
import {Props} from "next/script";
import {useProductValueContext} from "@/components/product/ProductInfo";

function ProductDescription(props) {
    const {productData} = useProductValueContext();
    return (
        <div>
            <div dangerouslySetInnerHTML={{__html:`${productData.description}`}}></div>
        </div>
    );
}

export default ProductDescription;