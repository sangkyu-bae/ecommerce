import React, {useState} from 'react';
import Button from "@mui/material/Button";
import AddIcon from "@mui/icons-material/Add";
import RemoveIcon from "@mui/icons-material/Remove";
import FormControlLabel from "@mui/material/FormControlLabel";
import Checkbox from "@mui/material/Checkbox";
import Inputs from "@/components/admin/Inputs";
import * as events from "events";

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
    const [isAllProduct,setIsAllProduct] = useState(false);

    const updateSize = (index:number, value) =>{

        let productSize = product.productComponents[index].sizes;

        if(value == 'All'){
            if(isAllProduct){
                productSize =[];
            }else{
                productSize = sizes;
                productSize.forEach(obj => delete obj.id);
            }
            setIsAllProduct(!isAllProduct);
        }else{
            productSize = productSize.filter(size => size.id != value)
            let size = sizes[value - 1];
            delete size.id;
            productSize.push(size);
        }

        updateProductComponent(index,productSize,'size');
    }

    const updateColor =(index : number,value : number) =>{
        const colorData = colors.find(data => data.id == value)
        updateProductComponent(index,colorData,"color");
    }

    return (
        <>

            <Inputs names={colors}
                    title={'color'}
                    width={100}
                    marginLeft={0}
                    register={register}
                    errors={errors}
                    onChangeEvent={updateColor}
                    index={index}
                    value={product.productComponents[index].color.id}
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
                            updateSize(index,e.target.value);
                        }
                    }
                />
                {
                    sizes.map((size, i: number) => (
                        <FormControlLabel
                            key={size.id}
                            control={
                                <Checkbox
                                    name={size.size}
                                    color="primary"
                                    value={size.id}
                                    checked={
                                        product.productComponents[index].sizes.some(
                                            (productSize) => productSize.id === size.id
                                        )
                                    }
                                />
                            }
                            label={size.size}
                            onChange={(e) => updateSize(index,e.target.value)}
                        />
                    ))
                }
            </div>
        </>
    );
}

export default SizeContainers;
