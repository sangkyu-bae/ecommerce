import React, {useMemo, useState} from 'react';

import Box from '@mui/material/Box';
import InputLabel from '@mui/material/InputLabel';
import MenuItem from '@mui/material/MenuItem';
import FormControl from '@mui/material/FormControl';
import Select from '@mui/material/Select';
import {useProductActionContext, useProductValueContext} from "@/components/product/ProductInfo";
function ProductSizeQuantity(props) {
    const {productData} = useProductValueContext();
    const actions = useProductActionContext();
    const [colorValue,setColorValue] = useState <number>(0);
    const [sizeValue, setSizeValue] = useState<number> (0);


    const onChangeColorValue = (value : number) =>{
        if(value == 0){
            setSizeValue(0);
        }
        setColorValue(value);
    }
    const isSelectProduct = colorValue != 0 && sizeValue != 0;

    const sizeData = useMemo(()=>{
        productData.productComponents
            .find(data => data.color.id == colorValue)
            ?.sizes || [];
    },[colorValue,productData])


    return (
        <Box sx={{ minWidth: 120 ,mt:2}}>
            <FormControl fullWidth>
                <InputLabel id="color">Color</InputLabel>
                <Select
                    labelId="color-select"
                    id="color-select"
                    value={colorValue}
                    label="color"
                    onChange={e=>{
                        onChangeColorValue(e.target.value)
                    }}
                    name = "color"
                >
                    <MenuItem value = {0}>옵션 선택</MenuItem>
                    {
                        productData.productComponents
                            .map(component => <MenuItem key ={component.color.id} value = {component.color.id}>{component.color.name}</MenuItem> )
                    }
                </Select>

                <InputLabel id="size-select">Size</InputLabel>
                <Select
                    sx={{mt:5}}
                    labelId="size-select"
                    id="size-select"
                    value={sizeValue}
                    label="Size"
                    onChange={e=>{
                        setSizeValue(e.target.value)
                    }}
                    name = "size"
                >
                    <MenuItem value = {0}>옵션 선택</MenuItem>
                    {
                        sizeData
                            .map(sizeComponent => <MenuItem key ={sizeComponent.id} value = {sizeComponent.id}>size : {sizeComponent.size} / quantity : {sizeComponent.quantity}</MenuItem> )
                    }

                </Select>

            </FormControl>
        </Box>
    );
}

export default ProductSizeQuantity;

