import React, {ChangeEvent, useEffect, useState} from 'react';
import TextField from "@mui/material/TextField";
import {MenuItem} from "@mui/material";
import {string} from "prop-types";
import Validation from "@/utils/Validation";

type ITest = {
    names: object[],
    title: string,
    width: number,
    marginLeft: number,
    register: object,
    errors: object,
    onChangeColorName : (event: ChangeEvent<HTMLInputElement>,fieId:string,type:string) =>void,
    value : number
}

function Input({names, title, width, marginLeft, register, errors,onChangeColorName,value}: ITest) {
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
            required
            id={title}
            label={labelTitle}
            {...register(title, {
                ...validation.test
            })}
            error={Boolean(errors.test)}
            name={title}
            onChange={onChangeColorName ?(e)=> onChangeColorName(e,'colorName','add'):null}
            autoFocus
            value={value != 0 && value}
        >

            {names && names.map((br, index) => (
                <MenuItem key={br.id} value={br.id}>
                    {br.name}
                </MenuItem>
            ))}
        </TextField>
    );
}

export default Input;