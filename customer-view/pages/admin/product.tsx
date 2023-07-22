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
            // 성공시 호출
            console.log(data.content);
            console.log(data.total);
        },
        onError: e => {
            // 실패시 호출 (401, 404 같은 error가 아니라 정말 api 호출이 실패한 경우만 호출됩니다.)
            // 강제로 에러 발생시키려면 api단에서 throw Error 날립니다. (참조: https://react-query.tanstack.com/guides/query-functions#usage-with-fetch-and-other-clients-that-do-not-throw-by-default)
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