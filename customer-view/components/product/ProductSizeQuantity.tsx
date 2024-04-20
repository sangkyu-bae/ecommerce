import React from 'react';

import Box from '@mui/material/Box';
import InputLabel from '@mui/material/InputLabel';
import MenuItem from '@mui/material/MenuItem';
import FormControl from '@mui/material/FormControl';
import Select from '@mui/material/Select';
import {useProductActionContext, useProductValueContext} from "@/components/product/ProductInfo";
function ProductSizeQuantity(props) {
    const {productData} = useProductValueContext();
    const actions = useProductActionContext();
    return (
        <Box sx={{ minWidth: 120 ,mt:2}}>
            <FormControl fullWidth>
                <InputLabel id="demo-simple-select-label">Age</InputLabel>
                <Select
                    labelId="demo-simple-select-label"
                    id="demo-simple-select"
                    // value={basket.productSizeId}
                    label="Size"
                    onChange={e=>actions.change(e)}
                    name = "productSizeId"
                >
                    {
                        productData.productComponents.map(component => (
                            component.sizes.map(size => (
                                <MenuItem key={size.id} value={size.id}>color: {component.color.name},size: {size.size}, quantity: {size.quantity} </MenuItem>
                            ))
                        ))
                    }
                </Select>
            </FormControl>
        </Box>
    );
}

export default ProductSizeQuantity;

