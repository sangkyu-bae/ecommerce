import React from 'react';
import {Props} from "next/script";
import {useMyContext} from "@/components/product/ProductInfo";

function ProductDescription(props) {
    const {productData,change} = useMyContext();
    return (
        <div>
            <div dangerouslySetInnerHTML={{__html:`${productData.description}`}}></div>
        </div>
    );
}

export default ProductDescription;