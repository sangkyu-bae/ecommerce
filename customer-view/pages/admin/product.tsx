import React, {useEffect, useState} from 'react';
import SideBar from "@/components/admin/sideBar";
import GridComponent, {StyledContainer, StyledContent, StyledMenu, StyledSetion} from "@/api/common/GridComponent";
import TextField from "@mui/material/TextField";
import Input from "@/components/admin/Input";
import Button from "@mui/material/Button";
import SendIcon from "@mui/icons-material/Send";
import CardComponent from "@/components/common/CardComponent";
import {useQuery} from "@tanstack/react-query";
import {ProductApi} from "../../api/product/ProductApi";

function Product(props) {
    const [productData, setProductData] = useState<ProductPageData | undefined>(undefined);
    const { data, isLoading, isError, error } = useQuery(['data'], ProductApi.readProduct,{
        onSuccess: data => {
            setProductData(data);
        },
        onError: e => {
            console.log(e.message);
        }
    });

    useEffect(()=>{
        console.log(productData)
    },[productData])
    return (
        <StyledContainer>
            <SideBar></SideBar>
            <StyledContent>
                <StyledSetion $isproduct={true}>
                    <div className="first-section">
                        <GridComponent title="👩‍🔧상품 관리"></GridComponent>
                        <div className="main-section">
                            {
                                productData &&
                                productData.productList.map((product,index)=>{
                                    return(
                                        <div className="flex" key={index}>
                                            <CardComponent></CardComponent>
                                            <CardComponent></CardComponent>
                                            <CardComponent></CardComponent>
                                        </div>
                                        )
                                })
                            }

                        </div>
                    </div>
                </StyledSetion>
            </StyledContent>
        </StyledContainer>
    );
}

export default Product;