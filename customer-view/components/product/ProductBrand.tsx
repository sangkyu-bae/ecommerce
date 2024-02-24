import React from 'react';
import {Props} from "next/script";

function ProductBrand({children}:Props) {
    return (
        <div className="image">
              <span className="gray">
                  {children}
              </span>
        </div>
    );
}

export default ProductBrand;
