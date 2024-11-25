import React, {useEffect} from 'react';
import {CircularProgress} from "@mui/material";
import Box from "@mui/material/Box";

function Loading(props) {
    return (
        <Box
            sx={{
                display: 'flex',
                justifyContent: 'center',
                alignItems: 'center',
                position: 'fixed',
                top: 0,
                left: 0,
                width: '100%',
                height: '100%',
                backgroundColor: 'rgba(0, 0, 0, 0.5)', // Optional: Adds a semi-transparent background
                zIndex: 9999, // Make sure the spinner is on top of other elements
            }}
        >
            <CircularProgress />
        </Box>
    );
}

export default Loading;