import React, {useEffect, useState} from 'react';
import GridComponent, {StyledContent, StyledSetion} from "@/components/common/GridComponent";
import {useQuery} from "@tanstack/react-query";
import {RankApi} from "@/shared/api/RankApi";
import Box from "@mui/material/Box";
import ProductCardComponent from "@/components/common/ProductCardComponent";
import Link from "next/link";
import {ProductApi} from "@/shared/api/product/ProductApi";
import {useProductTest} from "@/shared/hook/useProductTest";
import {CircularProgress} from "@mui/material";

function MainSection(props) {
    const {data,isLoading}=useQuery({
        context : undefined,
        queryKey : ['clickRank'],
        queryFn : () => RankApi.readClickRank(),
        select:undefined,
        enabled:true
    })

    return (
        <StyledContent isFull={true}>
            <StyledSetion isFull={true}>
                <GridComponent title={"💎베스트 상품"}/>
                <Box  sx={{width:'100%'}} >
                    <Box sx={{display:'flex', margin:'0 auto', width:'65%', flexWrap: 'wrap'}}>
                        {
                            // products.length > 0 && products.map(product=>
                             !isLoading && data.map(product=>
                                <Link   key={product.productId} href={`/product/${product.productId}`} style={{
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