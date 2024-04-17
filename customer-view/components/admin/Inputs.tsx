import React, {ChangeEvent, useEffect, useState} from 'react';
import TextField from "@mui/material/TextField";
import {MenuItem} from "@mui/material";
import {string} from "prop-types";
import Validation from "@/utils/Validation";
import {includes} from "superjson/dist/util";

type ITest = {
    names: object[],
    title: string,
    width: number,
    marginLeft: number,
    register: object,
    errors: object,
    onChangeEvent: (index: number, data:any,type: string) => void,
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
    const validation = Validation;
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
                {...register(title, {
                    ...validation[title]
                })}
                error={Boolean(errors[title])}
                name={title}
                onChange={(e) => {
                    onChangeEvent(index,e.target,'color');
                    changeValue(e)
                }}
                autoFocus
                value={componentValue}
            >
                {names && names.map((br, index) => (
                    <MenuItem key={br.id} value={br.id}>
                        {br.name}
                    </MenuItem>
                ))}
            </TextField>
            {/*<TextField*/}
            {/*    select*/}
            {/*    margin="normal"*/}
            {/*    style={{width: `${width}%`, marginLeft: `${marginLeft}%`}}*/}
            {/*    required*/}
            {/*    id={title}*/}
            {/*    label={title}*/}
            {/*    {...register(title, {*/}
            {/*        ...validation[title]*/}
            {/*    })}*/}
            {/*    error={Boolean(errors[title])}*/}
            {/*    name={title}*/}
            {/*    onChange={(e) => {*/}
            {/*        onChangeEvent(e, 'colorName', 'add');*/}
            {/*        changeValue(e)*/}
            {/*    }}*/}
            {/*    autoFocus*/}
            {/*    value={componentValue}*/}
            {/*>*/}
            {/*    {names && names.map((br, index) => (*/}
            {/*        <MenuItem key={br.id} value={br.id}>*/}
            {/*            {br.name}*/}
            {/*        </MenuItem>*/}
            {/*    ))}*/}
            {/*</TextField>*/}
        </>
    );
}

export default Inputs;

{/*{*/}
{/*    title.includes('color_0') &&*/}
{/*    <TextField*/}
{/*        select*/}
{/*        margin="normal"*/}
{/*        style={{width: `${width}%`, marginLeft: `${marginLeft}%`}}*/}
{/*        required*/}
{/*        id={title}*/}
{/*        label={labelTitle}*/}
{/*        error={Boolean(errors.test)}*/}
{/*        name={title}*/}
{/*        onChange={onChangeColorName ? (e) => {*/}
{/*            onChangeColorName(e, 'colorName', 'add');*/}
{/*            changeValue(e)*/}
{/*        } : (e) => changeValue(e)}*/}
{/*        autoFocus*/}
{/*        value={componentValue}*/}
{/*    >*/}
{/*        {names && names.map((br, index) => (*/}
{/*            <MenuItem key={br.id} value={br.id}>*/}
{/*                {br.name}*/}
{/*            </MenuItem>*/}
{/*        ))}*/}
{/*    </TextField>*/}
// }