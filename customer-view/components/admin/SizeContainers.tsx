import React from 'react';
import Input from "@/components/admin/Input";
import Button from "@mui/material/Button";
import AddIcon from "@mui/icons-material/Add";
import RemoveIcon from "@mui/icons-material/Remove";
import FormControlLabel from "@mui/material/FormControlLabel";
import Checkbox from "@mui/material/Checkbox";

type ISize = {
    colors : Data,
    product : Product
}

function SizeContainers({colors,product} : ISize) {


    return (
        <>
            <Input names={colors}
                   title={'color_' + index}
                   width={100}
                   marginLeft={0}
                   register={register}
                   errors={errors}
                   onChangeColorName={onChangeColorData}
                   value={colorProductData != null ? colorProductData.colorDto.id : 0}
            />
            {
                colorCnt == index + 1 && colorCnt < colors.length ?
                    <Button
                        variant="contained"
                        startIcon={<AddIcon/>}
                        style={{float: 'right'}}
                        onClick={() => setSizeColor('plus')}
                    >
                        add
                    </Button> :
                    <></>
            }
            {

                colorCnt == index + 1 && colorCnt > 1 ?
                    <Button
                        variant="contained"
                        startIcon={<RemoveIcon/>}
                        style={{float: 'right', marginRight: '1%'}}
                        color="error"
                        onClick={(e) => {
                            setSizeColor('remove')
                            onChangeColorData(e, '', 'remove')
                        }}
                    >
                        remove
                    </Button> :
                    <></>
            }
            <div> size</div>
            <div style={{display: 'flex', flexDirection: 'row'}}>
                <FormControlLabel
                    key='all'
                    control={
                        <Checkbox
                            name='all'
                            color="primary"
                        />
                    }
                    label='all'
                    onChange={(e) => onChangeColorData(e, 'colorSize', 'add')}
                />
                {sizes.map((size, index: number) => (
                    <FormControlLabel
                        key={size.id}
                        control={
                            <Checkbox
                                name={size.size}
                                color="primary"
                                value={size.id}
                                checked={colorProductData?.sizeQuantityDtoList.some(
                                    (sizeQuantityDto) => sizeQuantityDto.sizeDto.id === size.id
                                )}
                            />
                        }
                        label={size.size}
                        onChange={(e) => onChangeColorData(e, 'colorSize', 'add')}
                    />
                ))}
            </div>
        </>
    );
}

export default SizeContainers;
