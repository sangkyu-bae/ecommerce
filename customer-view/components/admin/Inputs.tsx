import React, {ChangeEvent, useEffect, useState} from 'react';
import TextField from "@mui/material/TextField";
import {MenuItem} from "@mui/material";


type ITest = {
    names: object[],
    title: string,
    width: number,
    marginLeft: number,
    register: object,
    errors: object,
    onChangeEvent: (index: number, value : number) => void,
    value: number
    index : number
}

function Inputs({
                    names,
                    title,
                    width,
                    marginLeft,
                    register,
                    errors,
                    onChangeEvent,
                    value,
                    index
                }: ITest) {
    const [labelTitle, setLabelTitle] = useState<String>("");
    useEffect(() => {
        setLabelTitle(title.includes("_") ? title.split("_")[0] : title);
    }, [])

    const [componentValue, setComponentValue] = useState(value);
    const changeValue = (e) => {
        setComponentValue(e.target.value)
    }

    return (
        <>
            <TextField
                select
                margin="normal"
                style={{width: `${width}%`, marginLeft: `${marginLeft}%`}}
                required
                id={title}
                label={title}

                error={Boolean(errors[title])}
                name={title}
                onChange={(e) => {
                    onChangeEvent(index,Number(e.target.value));
                    changeValue(e)
                }}
                autoFocus
                value={value}
            >
                {names && names.map((br, index) => (
                    <MenuItem key={br.id} value={br.id}>
                        {br.name}
                    </MenuItem>
                ))}
            </TextField>
        </>
    );
}

export default Inputs;