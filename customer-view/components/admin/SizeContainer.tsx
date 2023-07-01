import React, {useEffect} from 'react';
import FormControlLabel from "@mui/material/FormControlLabel";
import Checkbox from "@mui/material/Checkbox";
import Button from '@mui/material/Button';
import DeleteIcon from '@mui/icons-material/Delete';
import AddIcon from '@mui/icons-material/Add';
import Input from "@/components/admin/Input";
import RemoveIcon from '@mui/icons-material/Remove';

interface ISizeData {
    sizes: object[],
    colors: object[],
    colorCnt: number,
    index: number,
    setSizeColor: (element: string) => void
}

function SizeContainer({sizes, colors, setSizeColor, colorCnt, index}: ISizeData) {
    useEffect(() => {

    }, [])
    return (
        <>
            <Input names={colors}
                   title="color"
                   width={100}
                   marginLeft={0}/>
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
                        onClick={() => setSizeColor('remove')}
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
                />
                {sizes.map((size) => (
                    <FormControlLabel
                        key={size}
                        control={
                            <Checkbox
                                name={size.size}
                                color="primary"
                            />
                        }
                        label={size.size}
                    />
                ))}
            </div>
        </>
    );
}

export default SizeContainer;