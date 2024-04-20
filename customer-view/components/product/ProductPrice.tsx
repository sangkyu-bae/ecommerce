import React from 'react';
import {Props} from "next/script";
import {useProductValueContext} from "@/components/product/ProductInfo";

function ProductPrice(props) {
    const {productData} = useProductValueContext();
    return (
        <div>
            <span className="bold">
                <span>가격</span>
                <div>
                     <span className="gray">{productData.price}</span>
                </div>
            </span>
        </div>
    );
}

export default ProductPrice;