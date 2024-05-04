import React, {useEffect, useMemo, useState} from 'react';

import Box from '@mui/material/Box';
import InputLabel from '@mui/material/InputLabel';
import MenuItem from '@mui/material/MenuItem';
import FormControl from '@mui/material/FormControl';
import Select from '@mui/material/Select';
import {useProductActionContext, useProductValueContext} from "@/components/product/ProductInfo";
import {useDispatch} from "react-redux";
import {addBuyProduct} from "@/store/product/productRedux";

function ProductSizeQuantity(props) {
    const {productData} = useProductValueContext();
    const [colorValue, setColorValue] = useState<Data>({
        id: 0,
        name: ""
    });
    const [sizeValue, setSizeValue] = useState<Data>({
        id: 0,
        name: ""
    });
    const dispatch = useDispatch();

    const onChangeColorValue = (value) => {
        if (value == 0) {
            setSizeValue({
                name:'',
                id: 0
            });

            setColorValue({
                name:'',
                id:0
            })

            return;
        }

        const name: string = productData.productComponents
            .find(component => component.color.id == value).color.name;
        setColorValue({
            id: value,
            name: name
        });
    }

    const onChangeSizeValue = (value) => {

        if(value == 0){
            setSizeValue({
                id:0,
                name:''
            })

            return;
        }

        const name: string = productData.productComponents
            .find(data => data.color.id == colorValue.id)
            .sizes.find(size => size.id == value).size;

        setSizeValue({
            id: value,
            name: name
        });
    }


    useEffect(() => {
        if (colorValue.id != 0 && sizeValue.id != 0) {
            dispatch(addBuyProduct(colorValue,sizeValue));
            setColorValue({
                id:0,
                name:''
            });
            setSizeValue({
                id:0,
                name:''
            });
        }
    }, [colorValue, sizeValue])

    const sizeData = useMemo(() => {
        return productData.productComponents
            .find(data => data.color.id == colorValue.id)
            ?.sizes || [];
    }, [colorValue])


    return (
        <Box sx={{minWidth: 120, mt: 2}}>
            <FormControl fullWidth>
                <InputLabel id="color">Color</InputLabel>
                <Select
                    labelId="color-select"
                    id="color-select"
                    value={colorValue.id}
                    label="color"
                    onChange={e => {
                        onChangeColorValue(e.target.value)
                    }}
                    name="color"
                >
                    <MenuItem value={0}>옵션 선택</MenuItem>
                    {
                        productData.productComponents
                            .map(component => <MenuItem key={component.color.id}
                                                        value={component.color.id}>{component.color.name}</MenuItem>)
                    }
                </Select>

                <InputLabel id="size-select">Size</InputLabel>
                <Select
                    sx={{mt: 5}}
                    labelId="size-select"
                    id="size-select"
                    value={sizeValue.id}
                    label="Size"
                    onChange={e => {
                        onChangeSizeValue(e.target.value)
                    }}
                    name="size"
                >
                    <MenuItem value={0}>옵션 선택</MenuItem>
                    {
                        sizeData
                            .map(sizeComponent => <MenuItem key={sizeComponent.id} value={sizeComponent.id}>size
                                : {sizeComponent.size} / quantity : {sizeComponent.quantity}</MenuItem>)
                    }

                </Select>

            </FormControl>
        </Box>
    );
}

export default ProductSizeQuantity;

