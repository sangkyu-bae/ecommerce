import React from 'react';
import Typography from "@mui/material/Typography";
import Link from "@mui/material/Link";

function Copyright(props : any) {
    return (
        <Typography variant="body2" color="text.secondary" align="center" {...props}>
            {'Copyright © '}
            <Link color="inherit" href="https://mui.com/">
               신발쇼핑몰
            </Link>{' '}
            {new Date().getFullYear()}
            {'.'}
        </Typography>
    );
}

export default Copyright;