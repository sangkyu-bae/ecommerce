import React, {useContext} from 'react';
import {Props} from "next/script";

import { useMyContext} from "@/components/product/ProductInfo";
function ProductImage(props) {
    const {productData,change} = useMyContext();
    return (
        <div className="image">
            {productData.productImage}
        </div>
    );
}

export default ProductImage;