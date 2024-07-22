import React, {useEffect} from 'react';
import Box from "@mui/material/Box";
import GridComponent, {StyledContainer} from "@/components/common/GridComponent";
import {useProductSearch} from "@/shared/hook/useProductSearch";
import useCustomQuery from "@/shared/hook/useCustomQuery";
import {ProductApi} from "@/shared/api/product/ProductApi";
import {useQuery} from "@tanstack/react-query";
import Link from "next/link";
import ProductCardComponent from "@/components/common/ProductCardComponent";

function List(props) {

    const {data,
        isLoading,
        error,
        nextPage,
        pervPage
    } = useProductSearch(null,1);

    return (
        <StyledContainer>
            <GridComponent title={"💎상품"}>
                <Box  sx={{width:'100%'}} >
                    <Box sx={{display:'flex', margin:'0 auto', width:'70%'}}>
                        {
                            !isLoading && data.productVoList.map(product=>
                                <Link   key={product.productId} href={`/product/${product.id}`} style={{
                                    textDecoration: 'none',
                                    color: 'inherit',
                                    marginRight:'3em'
                                }}>
                                    <ProductCardComponent product={product}/>
                                </Link>

                            )
                        }
                    </Box>

                </Box>
            </GridComponent>
            <Box onClick={()=>nextPage()}>
                +
            </Box>
            <Box onClick={()=>pervPage()}>
                -
            </Box>
        </StyledContainer>
    );
}

export default List;