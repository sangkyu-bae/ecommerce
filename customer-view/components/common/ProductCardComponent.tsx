import React, {useEffect} from 'react';
import Card from "@mui/material/Card";
import {CardActionArea} from "@mui/material";
import CardMedia from "@mui/material/CardMedia";
import CardContent from "@mui/material/CardContent";
import Typography from "@mui/material/Typography";

function ProductCardComponent({product}:RankProduct) {
    useEffect(()=>{
        console.log(product);
    },[product])
    return (
        <Card style={{flex:'0.3' ,minWidth :'250px'}}>
            <CardActionArea>
                <CardMedia
                    component="img"
                    height="200"
                    image={product.productImage}
                    alt="green iguana"
                />
                <CardContent>
                    <Typography gutterBottom variant="h5" component="div">
                        {product.productName}
                    </Typography>
                </CardContent>
            </CardActionArea>
        </Card>
    );
}

export default ProductCardComponent;