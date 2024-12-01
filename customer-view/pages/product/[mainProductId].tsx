import React from "react";

import {ErrorBoundary} from "@/components/error/ErrorBoundary";
import ProductFetcher from "@/components/product/ProductFetcher";
import ProductContainer from "@/components/product/ProductContainer";

function DetailUserProduct() {

    return (
        <ErrorBoundary>
            <ProductFetcher>
                <ProductContainer/>
            </ProductFetcher>
        </ErrorBoundary>
    )

}

export default DetailUserProduct;