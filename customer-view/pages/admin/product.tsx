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
import Pagination from '@mui/material/Pagination';
import Stack from '@mui/material/Stack';
import {getTotalPageNumber} from "@/utils/CommonUtil";

function Product(props) {
    const [productData, setProductData] = useState<ProductPageData | undefined>(undefined);
    const [pageCnt, setPageCnt] = useState<number>(0);
    const [page,setPage] = useState<number>(1);
    const {data, isLoading, isError, error,refetch} = useQuery(
        ['data'],
        () => ProductApi.readProduct(page), {
        onSuccess: data => {
            // console.log(data)
            setProductData(data);
        },
        onError: e => {
            console.log(e.message);
        }
    });

    useEffect(() => {
        if(productData)
            setPageCnt(getTotalPageNumber(productData?.totalElements,productData?.pageSize))
    }, [productData])

    const changePageData =e=>{
        const pageTarget = parseInt(e.target.outerText);
        setPage(pageTarget)
    }

    useEffect(()=>{
        console.log(page)
        refetch()
    },[page])
    return (
        <StyledContainer>
            <SideBar></SideBar>
            <StyledContent>
                <StyledSetion $isproduct={true}>
                    <div className="first-section">
                        <GridComponent title="👩‍🔧상품 관리"></GridComponent>
                        <div className="main-section">
                            <div className="flex">
                                {
                                    productData &&
                                    productData.productList.map((product, index) => {
                                        return (
                                            <>
                                                <CardComponent
                                                    key={index}
                                                    product={product}
                                                    />
                                            </>
                                        )
                                    })
                                }
                            </div>
                            <div style={{ display: 'flex', justifyContent: 'center' ,marginTop:'3%'}}>
                                {
                                    pageCnt != 0 && <Pagination count={pageCnt} onChange={(e)=>changePageData(e)}/>
                                }
                            </div>

                        </div>
                    </div>
                </StyledSetion>
            </StyledContent>
        </StyledContainer>
    );
}

export default Product;