import React from 'react';
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
    const { data, isLoading, isError, error } = useQuery(['data'], ProductApi.readProduct,{
        onSuccess: data => {
            console.log(data);
        },
        onError: e => {
            console.log(e.message);
        }
    });


    return (
        <StyledContainer>
            <SideBar></SideBar>
            <StyledContent>
                <StyledSetion $isproduct={true}>
                    <div className="first-section">
                        <GridComponent title="👩‍🔧상품 관리"></GridComponent>
                        <div className="main-section">
                            <div className="flex">
                                <CardComponent></CardComponent>
                                <CardComponent></CardComponent>
                                <CardComponent></CardComponent>
                                <CardComponent></CardComponent>
                            </div>
                        </div>
                    </div>
                </StyledSetion>
            </StyledContent>
        </StyledContainer>
    );
}

export default Product;