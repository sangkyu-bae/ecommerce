import React from 'react';
import {Props} from "next/script";

function ProductDescription({children}: Props) {
    return (
        <div>
            <div dangerouslySetInnerHTML={{__html:'tt'}}></div>
        </div>
    );
}

export default ProductDescription;