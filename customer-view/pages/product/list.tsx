import React, {useEffect} from 'react';
import Box from "@mui/material/Box";
import {StyledContainer} from "@/components/common/GridComponent";
import {useProductSearch} from "@/shared/hook/useProductSearch";
import useCustomQuery from "@/shared/hook/useCustomQuery";
import {ProductApi} from "@/shared/api/product/ProductApi";
import {useQuery} from "@tanstack/react-query";

function List(props) {

    const {data,isLoading,error,nextPage} = useProductSearch(null,1);

    const handleClick = () =>{
        nextPage(data.pageNumber+1);
    }
    useEffect(()=>{
        console.log(data);
    },[data])
    return (
        <StyledContainer>
            <Box onClick={handleClick}>
                +
            </Box>
        </StyledContainer>
    );
}

export default List;