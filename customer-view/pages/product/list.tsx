import React, {useEffect} from 'react';
import Box from "@mui/material/Box";
import GridComponent, {StyledContainer, StyledSetion} from "@/components/common/GridComponent";
import {useProductSearch} from "@/shared/hook/useProductSearch";

import ProductCardComponent from "@/components/common/ProductCardComponent";
import styled from "styled-components";
import Pagination from "@mui/material/Pagination";
import Link from "next/link";

function List(props) {
    const ProductContainer = styled(Box)`
      display: flex;
      flex-wrap: wrap;
      margin: 0 auto;
      width: 80%;
    `;

    const ProductLink = styled.div`
      text-decoration: none;
      color: inherit;
      width: calc(25% - 3em);
      margin-right: 3em;
      margin-bottom: 1em;
    
      &:nth-of-type(4n) {
        margin-right: 0;
      }
    `;
    const {data,
        isLoading,
        error,
        movePage,
        getTotalPage,
        getPage
    } = useProductSearch(null,1);

    const handleChange = (event, value) => {
        movePage(value);
    };


    return (
        <StyledContainer isFull={true}>
            <StyledSetion isFull={true}>
            <GridComponent title={"💎상품"}>
                <Box  sx={{width:'100%'}} >
                    <ProductContainer>
                        {!isLoading && data.productVoList.map(product => (
                            <Link key={product.productId}
                                  href={`/product/${product.id}?searchPage=${getPage()}`}
                                  style={{
                                      textDecoration: 'none',
                                      color: 'inherit',
                                  }}
                                  passHref>
                                <ProductLink>
                                    <ProductCardComponent
                                        image={product.productImage}
                                        productName={product.name}
                                    />
                                </ProductLink>
                            </Link>
                        ))}
                    </ProductContainer>

                </Box>
                <Box sx={{width:'28%', margin:'0 auto'}}>
                    <Pagination count={getTotalPage()}
                                size="large"
                                showFirstButton showLastButton
                                onChange={handleChange}
                    />
                </Box>
            </GridComponent>

            </StyledSetion>
        </StyledContainer>
    );
}

export default List;