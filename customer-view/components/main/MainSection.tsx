import React, {useEffect, useState} from 'react';
import GridComponent, {StyledContent, StyledSetion} from "@/components/common/styles/GridComponent";
import Box from "@mui/material/Box";
import ProductCardComponent from "@/components/common/ProductCardComponent";
import Link from "next/link";

function MainSection({data}) {

    return (
        <StyledContent isFull={true}>
            <StyledSetion isFull={true}>
                <GridComponent title={"💎베스트 상품"}/>
                <Box  sx={{width:'100%'}} >
                    <Box sx={{display:'flex', margin:'0 auto', width:'65%', flexWrap: 'wrap'}}>
                        {
                                data.map(product=>
                                <Link  key={product.productId} href={`/product/${product.productId}`} style={{
                                    textDecoration: 'none',
                                    color: 'inherit',
                                    marginLeft:'4em',
                                    marginTop:'2em'
                                }}>
                                    <ProductCardComponent
                                        image={product.productImage}
                                        productName={product.productName}
                                        minWidth={'330px'}
                                    />
                                </Link>

                            )
                        }
                    </Box>
                </Box>

            </StyledSetion>

        </StyledContent>

    );
}

export default MainSection;