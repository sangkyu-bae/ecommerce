import React from 'react';
import SelectBox from "@/components/common/SelectBox";
import Button from "@mui/material/Button";
import {useProductActionContext, useProductValueContext} from "@/components/product/ProductInfo";
import Box from "@mui/material/Box";

function ProductUpdateButton(props) {
    const actions = useProductActionContext();
    const {productData} = useProductValueContext();

    return (
        <Box style={{}}>
            <div className="bold">
                <SelectBox productComponents ={productData.productComponents}/>
                <Button variant="outlined" sx={{marginTop: 2}} onClick={actions.handleUpdateButtonClick}>
                    수정하기
                </Button>
            </div>
        </Box>
    );
}

export default ProductUpdateButton;