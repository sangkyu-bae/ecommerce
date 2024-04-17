import React from 'react';
import Button from "@mui/material/Button";
import AddIcon from "@mui/icons-material/Add";
import RemoveIcon from "@mui/icons-material/Remove";
import FormControlLabel from "@mui/material/FormControlLabel";
import Checkbox from "@mui/material/Checkbox";
import Inputs from "@/components/admin/Inputs";

type ISize = {
    colors: Data[],
    sizes: object[]
    product: Product,
    errors: object,
    register: object,
    index: number,
    addProductComponent(): void,
    updateProductComponent(index: number, data:any, type:string): void,
    removeProductComponent(index: number): void
}

function SizeContainers({
                            colors,
                            product,
                            register,
                            errors,
                            sizes,
                            index,
                            addProductComponent,
                            updateProductComponent,
                            removeProductComponent
                        }: ISize) {
    return (
        <>

            <Inputs names={colors}
                    title={'color'}
                    width={100}
                    marginLeft={0}
                    register={register}
                    errors={errors}
                    onChangeEvent={updateProductComponent}
                    index={index}
                //    value={colorProductData != null ? colorProductData.colorDto.id : 0}
            />
            {
                product.productComponents.length < colors.length &&
                index + 1 == product.productComponents.length &&
                <Button
                    variant="contained"
                    startIcon={<AddIcon/>}
                    style={{float: 'right'}}
                    onClick={() => addProductComponent()}
                >
                    add
                </Button>
            }
            {

                product.productComponents.length > 1 &&
                index + 1 == product.productComponents.length&&
                <Button
                    variant="contained"
                    startIcon={<RemoveIcon/>}
                    style={{float: 'right', marginRight: '1%'}}
                    color="error"
                    onClick={(e) => removeProductComponent(index)}
                >
                    remove
                </Button>
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
                    value={'All'}
                    onChange={
                        (e) =>{
                            updateProductComponent(index,e.target.value,'size');
                        }
                    }
                />
                {
                    sizes.map((size, index: number) => (
                        <FormControlLabel
                            key={size.id}
                            control={
                                <Checkbox
                                    name={size.size}
                                    color="primary"
                                    value={size.id}
                                    // checked={colorProductData?.sizeQuantityDtoList.some(
                                    //     (sizeQuantityDto) => sizeQuantityDto.sizeDto.id === size.id
                                    // )}
                                />
                            }
                            label={size.size}
                            // onChange={(e) => onChangeColorData(e, 'colorSize', 'add')}
                        />
                    ))
                }
            </div>
        </>
    );
}

export default SizeContainers;
