import React from 'react';
import {Props} from "next/script";

function ProductPrice({children}:Props) {
    return (
        <div>
            <span className="bold">
                <span>가격</span>
                <span className="gray">{children}</span>
            </span>
        </div>
    );
}

export default ProductPrice;