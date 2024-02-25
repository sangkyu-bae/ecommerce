import React, {createContext, useContext, useEffect} from 'react';
import Util from "@/utils/CommonUtil";
import {useRouter} from "next/router";
import {MyProduct} from "@/store/product/myProduct";
import ProductImage from "@/components/product/ProductImage";
import ProductBrand from "@/components/product/ProductBrand";
import ProductPrice from "@/components/product/ProductPrice";
import ProductDescription from "@/components/product/ProductDescription";
import GridComponent, {StyledContainer, StyledContent, StyledSetion} from "@/components/common/GridComponent";
import ProductUpdateButton from "@/components/product/ProductUpdateButton";

interface InfoProps {
    productData: MyProduct,
    children: React.ReactNode
}
const MyContext = createContext({});
export function useMyContext() {
    return useContext(MyContext);
}
function ProductInfo({productData, children}: InfoProps) {
    const util = new Util();
    const router = useRouter();

    const handleUpdateButtonClick = () => {
        const {productId}: number = router.query;
        router.push(`/admin/product/update/${productId}`)
    }

    return (
        <MyContext.Provider value ={productData}>
            <StyledContainer>
                <StyledContent isFull={true}>
                    <StyledSetion isFull={true}>
                        <div className="first-section">
                            <GridComponent title={`📰${productData?.name}`}></GridComponent>
                            <div className="main-section">
                                {children}
                            </div>
                        </div>
                    </StyledSetion>
                </StyledContent>
            </StyledContainer>
        </MyContext.Provider>
    );
}

ProductInfo.ProductImage = ProductImage;
ProductInfo.ProductBrand = ProductBrand;
ProductInfo.ProductPrice = ProductPrice;
ProductInfo.ProductDescription = ProductDescription;
ProductInfo.ProductUpdateButton = ProductUpdateButton;

export default ProductInfo;