import React from 'react';
import TextField from "@mui/material/TextField";
import {MenuItem} from "@mui/material";
import {string} from "prop-types";
interface ITest {
    names: object[],
    title : string,
    width : number,
    marginLeft : number
}
function Input({names,title,width,marginLeft} : ITest) {

    return (
        <TextField
            select
            margin="normal"
            style={{ width: `${width}%` ,marginLeft:`${marginLeft}%`}}
            required
            id={title}
            label={title}
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