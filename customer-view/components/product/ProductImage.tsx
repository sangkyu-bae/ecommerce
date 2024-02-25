import React, {useContext} from 'react';
import {Props} from "next/script";

import { useMyContext} from "@/components/product/ProductInfo";
function ProductImage(props) {
    const product= useMyContext();
    return (
        <div className="image">
            {product.productImage}
        </div>
    );
}

export default ProductImage;