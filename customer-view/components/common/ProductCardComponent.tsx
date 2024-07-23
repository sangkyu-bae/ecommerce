import React, {useEffect} from 'react';
import Card from "@mui/material/Card";
import {CardActionArea} from "@mui/material";
import CardMedia from "@mui/material/CardMedia";
import CardContent from "@mui/material/CardContent";
import Typography from "@mui/material/Typography";

function ProductCardComponent({image,productName}) {

    return (
        <Card style={{flex:'1' ,minWidth :'300px'}}>
            <CardActionArea>
                <CardMedia
                    component="img"
                    height="200"
                    image={image}
                    alt="green iguana"
                />
                <CardContent>
                    <Typography gutterBottom variant="h5" component="div">
                        {productName}
                    </Typography>
                </CardContent>
            </CardActionArea>
        </Card>
    );
}

export default ProductCardComponent;