import React from 'react';
import {Props} from "next/script";
import {useMyContext} from "@/components/product/ProductInfo";

function ProductDescription(props) {
    const product= useMyContext();
    return (
        <div>
            <div dangerouslySetInnerHTML={{__html:`${product.description}`}}></div>
        </div>
    );
}

export default ProductDescription;