import React from 'react';
import {Props} from "next/script";

function ProductImage({children}: Props) {
    return (
        <div className="image">
            {children}
        </div>
    );
}

export default ProductImage;