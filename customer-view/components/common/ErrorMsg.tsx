import React from 'react';
import Box from "@mui/material/Box";
import Typography from "@mui/material/Typography";

interface ErrorMessageProps {
    message: string | null;
    highlightWord?: string | null;
}

function ErrorMsg({ message, highlightWord }: ErrorMessageProps) {
    if (!message) return null;

    const parts = highlightWord 
        ? message.split(highlightWord)
        : [message];

    return (
        <Box 
            sx={{ 
                mt: 2,
                mb: 2,
                textAlign: 'center',
                fontSize: '0.875rem'
            }}
        >
            {parts.map((part, index) => (
                <React.Fragment key={index}>
                    {part}
                    {index < parts.length - 1 && (
                        <Typography 
                            component="span" 
                            sx={{ 
                                color: 'error.main',
                                fontWeight: 'bold'
                            }}
                        >
                            {highlightWord}
                        </Typography>
                    )}
                </React.Fragment>
            ))}
        </Box>
    );
}

export default ErrorMsg;   