import React from 'react';
import FormControlLabel from "@mui/material/FormControlLabel";
import Checkbox from "@mui/material/Checkbox";
import Button from '@mui/material/Button';
import DeleteIcon from '@mui/icons-material/Delete';
import AddIcon from '@mui/icons-material/Add';
interface ISizeData{
    sizes : object[]
}

function SizeContainer({sizes}:ISizeData) {
    console.log(sizes)
    return (
        <>
            <Button variant="contained" startIcon={<AddIcon />} style={{float: 'right'}}>
                add
            </Button>

            <div> size </div>
            <div style={{ display: 'flex', flexDirection: 'row' }}>
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