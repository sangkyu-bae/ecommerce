import React, {useContext} from 'react';
import {Props} from "next/script";

import { useProductValueContext} from "@/components/product/ProductInfo";
function ProductImage(props) {
    const {productData} = useProductValueContext();
    return (
        <div className="image">
            {productData.productImage}
        </div>
    );
}

export default ProductImage;