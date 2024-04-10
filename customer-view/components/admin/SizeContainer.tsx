import React, {ChangeEvent, useEffect, useState} from 'react';
import FormControlLabel from "@mui/material/FormControlLabel";
import Checkbox from "@mui/material/Checkbox";
import Button from '@mui/material/Button';
import DeleteIcon from '@mui/icons-material/Delete';
import AddIcon from '@mui/icons-material/Add';
import Input from "@/components/admin/Input";
import RemoveIcon from '@mui/icons-material/Remove';
import Validation from "@/utils/Validation";
import AdminFunc from "@/components/admin/AdminFunc";

interface ISizeData {
    sizes: object[],
    colors: object[],
    colorCnt: number,
    index: number,
    setSizeColor: (element: string) => void,
    errors: object,
    register: object
    handleChangeColorData: (colorData: ColorData, type: string) => void,
    colorProductData : colorProductData | null
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
                           handleChangeColorData,
                           colorProductData
                       }: ISizeData) {
    const adminFunc = new AdminFunc();
    // const [colorData, setColorData] = useState<ColorData>({
    //     // colorName: '',
    //     colorDto:{
    //         id:0,
    //         name:''
    //     },
    //     colorSize: []
    // })
    const [colorData, setColorData] = useState<ProductComponent>({
        color:{
            id:0,
            name:''
        },
        id:0,
        sizes: []
    })

    useEffect(()=>{
        if(colorProductData){
            setColorData(adminFunc.toColorData(colorProductData));
            setType('add')
        }
    },[])
    const [type, setType] = useState<string>("");
    const onChangeColorData = (event: ChangeEvent<HTMLInputElement>, fieId: string, type: string) => {
        const checked = event.target.checked
        if (type == 'add') {
            if (fieId == 'colorName') {
                // setColorData({
                //     ...colorData,
                //     colorName: event.target.value
                // })
                setColorData({
                    ...colorData,
                    colorDto:{
                        id:parseInt(event.target.value),
                        name:''
                    }
                })
            } else if (fieId == 'colorSize') {

                const size = event.target.name
                const id = event.target.value;
                let sizeValue: Size = {
                    size: parseInt(size),
                    id: parseInt(id),
                };
                settingColorSize(sizeValue, checked);
            }
            setType("add")
        } else if (type == 'remove') {
            handleChangeColorData(colorData, 'remove');
            setType("remove")
        }
    }

    const settingColorSize = (value: Size, checked: boolean) => {
        let {colorSize} = colorData;
        if (checked) {
            colorSize.push({
                size: value,
                quantity: 100
            })
        } else {
            colorSize = colorSize.filter(obj => obj.size.id != value.id);
        }
        setColorData({
            ...colorData,
            colorSize
        })
    }

    useEffect(() => {
        if (type == 'add' && colorData) {
            handleChangeColorData(colorData, 'add');
        }
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

export default SizeContainer;