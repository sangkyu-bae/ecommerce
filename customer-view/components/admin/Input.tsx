import React, {useEffect, useState} from 'react';
import TextField from "@mui/material/TextField";
import {MenuItem} from "@mui/material";
import {string} from "prop-types";
import Validation from "@/components/common/Validation";

interface ITest {
    names: object[],
    title: string,
    width: number,
    marginLeft: number,
    register: object,
    errors: object
}

function Input({names, title, width, marginLeft, register, errors}: ITest) {
    const validation = Validation;
    const [labelTitle,setLabelTitle]=useState<String>("");
    useEffect(() => {
        setLabelTitle( title.includes("_") ? title.split("_")[0] : title);
    }, [])

    return (
        <TextField
            select
            margin="normal"
            style={{width: `${width}%`, marginLeft: `${marginLeft}%`}}
            // required
            id={title}
            label={labelTitle}
            {...register(title, {
                ...validation.test
            })}
            error={Boolean(errors.test)}
            name={title}
            autoFocus
        >
            {names && names.map((br, index) => (
                <MenuItem key={index} value={br.name}>
                    {br.name}
                </MenuItem>
            ))}
        </TextField>
    );
}

export default Input;