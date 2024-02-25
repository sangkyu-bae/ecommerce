import React from 'react';
import {Props} from "next/script";
import {useMyContext} from "@/components/product/ProductInfo";

function ProductPrice(props) {
    const product = useMyContext();
    return (
        <div>
            <span className="bold">
                <span>가격</span>
                <div>
                     <span className="gray">{product.price}</span>
                </div>
            </span>
        </div>
    );
}

export default ProductPrice;