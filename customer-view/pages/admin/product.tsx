import React, {useEffect, useState} from 'react';
import SideBar from "@/components/admin/sideBar";
import GridComponent, {StyledContainer, StyledContent, StyledMenu, StyledSetion} from "@/components/common/GridComponent";

import CardComponent from "@/components/common/CardComponent";
import {useQuery} from "@tanstack/react-query";
import {ProductApi} from "../../shared/api/product/ProductApi";
import Pagination from '@mui/material/Pagination';
import Link from "next/link";
import Util from "@/shared/utils/CommonUtil";

function Product(props) {
    const [productData, setProductData] = useState<ProductPageData | undefined>(undefined);
    const [pageCnt, setPageCnt] = useState<number>(0);
    const [page,setPage] = useState<number>(1);
    const util = new Util();
    const {data, isLoading, isError, error,refetch} = useQuery(
        ['data'],
        () => ProductApi.readProduct(page), {
        onSuccess: data => {
            console.log(data)
            setProductData(data);
        },
        onError: e => {
            console.log(e.message);
        }
    });

    useEffect(() => {
        if(productData){
            const pageCnt = util.getTotalPageNumber(productData.totalElements, productData.pageSize);
            setPageCnt(pageCnt);
        }

    }, [productData])

    const changePageData =e=>{
        const pageTarget = parseInt(e.target.outerText);
        setPage(pageTarget)
    }

    useEffect(()=>{
        refetch()
    },[page])
    return (
        <StyledContainer>
            <SideBar type={'admin'}></SideBar>
            <StyledContent>
                <StyledSetion $isproduct={true}>
                    <div className="first-section">
                        <GridComponent title="👩‍🔧상품 관리"></GridComponent>
                        <div className="main-section">
                            <div className="flex">
                                {
                                    productData &&
                                    productData.productVoList.map((product, index) => {
                                        return (
                                            <>
                                                <Link
                                                    style={{
                                                        textDecoration: 'none',
                                                        color: 'inherit',
                                                        width:'32%',
                                                        margin:'0.5em'
                                                     }}
                                                    href={{
                                                        pathname: `/admin/product/[productId]`,
                                                        query: { productId: `${product.id}` },
                                                    }}
                                                >
                                                    <CardComponent
                                                        key={index}
                                                        product={product}
                                                    />
                                                </Link>
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