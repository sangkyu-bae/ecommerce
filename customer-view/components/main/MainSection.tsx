import React, {useState} from 'react';
import GridComponent, {StyledContent, StyledSetion} from "@/api/common/GridComponent";
import {useQuery} from "@tanstack/react-query";
import {RankApi} from "@/api/RankApi";
import Box from "@mui/material/Box";
import ProductCardComponent from "@/components/common/ProductCardComponent";

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
                <Box  sx={{display:'flex'}} >
                {

                    products.length > 0 && products.map(product=>
                        <ProductCardComponent key={product.id} product={product}/>
                    )
                }
                </Box>
            </StyledSetion>

        </StyledContent>

    );
}

export default MainSection;