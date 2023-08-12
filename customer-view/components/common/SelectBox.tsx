import * as React from 'react';
import Box from '@mui/material/Box';
import InputLabel from '@mui/material/InputLabel';
import MenuItem from '@mui/material/MenuItem';
import FormControl from '@mui/material/FormControl';
import Select, { SelectChangeEvent } from '@mui/material/Select';
import {useEffect} from "react";
function SelectBox({colorProduct}:{colorProduct:colorProductData[]}) {
    useEffect(()=>{
        console.log(colorProduct)
    },[colorProduct])

    const handleChange = (event: SelectChangeEvent) => {
        // setAge(event.target.value as string);
    };

    return (
        <Box sx={{ minWidth: 120 ,maxWidth:280, marginTop:2}}>
            <FormControl fullWidth>
                <InputLabel id="demo-simple-select-label">색상/사이즈 수량</InputLabel>
                <Select
                    labelId="demo-simple-select-label"
                    id="demo-simple-select"
                    // value={age}
                    label="색상/사이즈 수량"
                    onChange={handleChange}
                >
                    {
                        colorProduct.map(colorProduct=>{
                            return colorProduct.sizeQuantityDtoList.map(sizeQuantity=>{
                                return   <MenuItem key ={sizeQuantity.sizeDto.id} >색상 {colorProduct.colorDto.name}/사이즈 {sizeQuantity.sizeDto.size} /수량 {sizeQuantity.quantity}</MenuItem>
                            })

                        })
                    }
                </Select>
            </FormControl>
        </Box>
    );
}

export default SelectBox;