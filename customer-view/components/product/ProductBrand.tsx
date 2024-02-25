import React from 'react';
import {Props} from "next/script";
import {useMyContext} from "@/components/product/ProductInfo";

function ProductBrand(props) {
    const product = useMyContext();
    return (
        <div >
                <span className="bold"><span>브랜드 / 카테고리</span>
                    <div>
                           <span className="gray">
                        {product?.brand && product.brand.name} / {product?.category && product.category.name}
                    </span>
                    </div>

                </span>
        </div>
    );
}

export default ProductBrand;
