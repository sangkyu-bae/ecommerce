import React from 'react';
import Box from "@mui/material/Box";
import Card from "@mui/material/Card";
import CardContent from "@mui/material/CardContent";
import {CardActions, Typography} from "@mui/material";
import Button from "@mui/material/Button";
type componentProps = {
    title : string,
    subTitle : string
    btnTitle : string
    clickEvent : void
}
function CardCouponComponent({title,btnTitle,subTitle,clickEvent} : componentProps) {
    return (
        <Box sx = {{width:"100%"}}>
            <Box sx = {{width:"100%"}}>
                <Box sx={{ minWidth: 500,maxWidth : '50%', margin:'2em auto', flex:1, flexDirection: 'column'}}>
                    <Card variant="outlined">
                        <React.Fragment>
                            <CardContent sx={{padding:'3%', textAlign:'center'}}>
                                <Typography variant="h5" component="div">
                                    {title}
                                </Typography>
                                <Typography variant="body2">
                                    {subTitle}
                                </Typography>
                            </CardContent>
                            <CardActions>
                                <Button sx={{margin:"0 auto"}} variant="contained">{btnTitle}</Button>
                            </CardActions>
                        </React.Fragment>
                    </Card>
                </Box>
            </Box>
        </Box>
    );
}

export default CardCouponComponent;