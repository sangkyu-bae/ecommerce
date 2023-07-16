import React, {ChangeEvent, useEffect, useState} from 'react';
import FormControlLabel from "@mui/material/FormControlLabel";
import Checkbox from "@mui/material/Checkbox";
import Button from '@mui/material/Button';
import DeleteIcon from '@mui/icons-material/Delete';
import AddIcon from '@mui/icons-material/Add';
import Input from "@/components/admin/Input";
import RemoveIcon from '@mui/icons-material/Remove';
import Validation from "@/components/common/Validation";

interface ISizeData {
    sizes: object[],
    colors: object[],
    colorCnt: number,
    index: number,
    setSizeColor: (element: string) => void,
    errors: object,
    register: object
    handleChangeColorData: ( colorData: ColorData, type: string) => void
}

const validation = Validation;

function SizeContainer({
                           sizes,
                           colors,
                           setSizeColor,
                           colorCnt,
                           index,
                           register,
                           errors,
                           handleChangeColorData
                       }: ISizeData) {
    const [colorData, setColorData] = useState<ColorData>({
        colorName: '',
        colorSize: []
    })
    const onChangeColorData = (event: ChangeEvent<HTMLInputElement>, fieId: string, type:string) => {
        const checked = event.target.checked
        if(type=='add'){
            if (fieId == 'colorName') {
                setColorData({
                    ...colorData,
                    colorName: event.target.value
                })
            } else if (fieId == 'colorSize') {

                const size = event.target.value
                const id = event.target.name;
                let sizeValue: Size = {
                    size: parseInt(size),
                    id: parseInt(id),
                };

                settingColorSize(sizeValue, checked);
            }
        }else if(type =='remove'){
            handleChangeColorData(colorData,'remove');
        }
    }

    const settingColorSize = (value: Size, checked: boolean) => {
        console.log(value)
        let {colorSize} = colorData;
        if (checked) {
            colorSize.push({
                size: value,
                quantity: 100
            })
        }
        setColorData({
            ...colorData,
            colorSize
        })
        // if (name == 'all') {
        //     if (checked) {
        //         const sizeQuantityData = sizes.map((size: { size: number }) => {
        //             return {
        //                 size: size.size,
        //                 quantity: 100
        //             };
        //         });
        //         colorSize = sizeQuantityData;
        //     } else {
        //         colorSize = [];
        //     }
        // } else {
        //     const sizeName: number = parseInt(name)
        //     if (checked) {
        //         colorSize.push({
        //             size: sizeName,
        //             quantity: 100
        //         })
        //     } else {
        //         colorSize = colorSize.filter(color => color.size != sizeName);
        //     }
        // }
        // setColorData({
        //     ...colorData,
        //     colorSize
        // })
    }

    useEffect(() => {
        // const {colorName,colorSize} = colorData;
        // if(colorName) handleChangeColorData(colorData,'add');
        console.log(colorData)
    }, [colorData])
    return (
        <>
            <Input names={colors}
                   title={'color_' + index}
                   width={100}
                   marginLeft={0}
                   register={register}
                   errors={errors}
                   onChangeColorName={onChangeColorData}
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
                            onChangeColorData(e, '','remove')
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
                    onChange={(e) => onChangeColorData(e, 'colorSize','add')}
                />
                {sizes.map((size, index: number) => (
                    <FormControlLabel
                        key={size.id}
                        control={
                            <Checkbox
                                name={size.size}
                                color="primary"
                                value={size.id}
                            />
                        }
                        label={size.size}
                        onChange={(e) => onChangeColorData(e, 'colorSize','add')}
                    />
                ))}
            </div>
        </>
    );
}

export default SizeContainer;