import React from 'react';
import SideBar from "@/components/admin/sideBar";
import GridComponent, {StyledContainer, StyledContent, StyledMenu, StyledSetion} from "@/api/common/GridComponent";
import TextField from "@mui/material/TextField";
import Input from "@/components/admin/Input";
import Button from "@mui/material/Button";
import SendIcon from "@mui/icons-material/Send";
import CardComponent from "@/components/common/CardComponent";

function Product(props) {
    return (
        <StyledContainer>
            <SideBar></SideBar>
            <StyledContent>
                <StyledSetion isProduct={true}>
                    <div className="first-section">
                        <GridComponent title="👩‍🔧상품 관리"></GridComponent>
                        <div className="main-section">
                            <CardComponent></CardComponent>
                            <CardComponent></CardComponent>
                        </div>
                    </div>
                </StyledSetion>
            </StyledContent>
        </StyledContainer>
    );
}

export default Product;