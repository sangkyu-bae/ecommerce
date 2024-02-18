import React, {useState} from 'react';
import GridComponent, {StyledContent, StyledSetion} from "@/components/common/GridComponent";
import {useQuery} from "@tanstack/react-query";
import {RankApi} from "@/api/RankApi";
import Box from "@mui/material/Box";
import ProductCardComponent from "@/components/common/ProductCardComponent";
import Link from "next/link";

function MainSection(props) {

    const [products, setProducts] = useState<RankProduct[]>([]);

    const {data}=useQuery(
        ['clickRank'],
        ()=>RankApi.readClickRank(), {
            onSuccess: data =>{
                console.log(data);
                setProducts(data);
            }
        }
    )
    return (
        <StyledContent isFull={true}>
            <StyledSetion isFull={true}>
                <GridComponent title={"💎베스트 상품"}/>
                <Box  sx={{display:'flex', width:'100%'}} >
                {
                    products.length > 0 && products.map(product=>
                        <Link   key={product.productId} href={`/product/${product.productId}`} style={{
                            textDecoration: 'none',
                            color: 'inherit',
                        }}>
                            <ProductCardComponent product={product}/>
                        </Link>

                    )
                }
                </Box>
                <GridComponent title={"상품"}/>
                <Box  sx={{display:'flex', width:'100%'}} >
                    {
                        products.length > 0 && products.map(product=>
                            <ProductCardComponent key={product.productId} product={product}/>
                        )
                    }
                </Box>
            </StyledSetion>

        </StyledContent>

    );
}

export default MainSection;