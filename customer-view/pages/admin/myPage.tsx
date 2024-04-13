import React, {ChangeEvent, useEffect, useRef, useState} from 'react';
import styled from "styled-components";
import dynamic from "next/dynamic";
import ProductAdmin from "@/viewer/ProductAdmin";
import UploadProductComponent from "@/viewer/UploadProductComponent";


const NoSsrEditor = dynamic(() => import('../../components/common/' + 'ReactEdit'), {ssr: false});
const StyledContainer = styled.div`
        display : flex;
        width : 100%;
        height : 100vh;
    `

function MyPage() {
    const emptyProduct: Product = {
        id: 0,
        name: "",
        price: 0,
        productImage: "",
        description: "",
        brand: {},
        category: {},
        colorDataList: []
    };
    return (
        // <ProductAdmin isCreate={true}
        //               title="🛒상품 등록"
        //               buttonTitle="상품등록"
        //               severProductData={emptyProduct}
        // />
        <UploadProductComponent title="🛒상품 등록" buttonTitle="상품등록" initProductData={emptyProduct}/>
    )

}

export default MyPage;