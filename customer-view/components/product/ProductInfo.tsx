import React, {useEffect} from 'react';
import Util from "@/utils/CommonUtil";
import SelectBox from "@/components/common/SelectBox";
import Button from "@mui/material/Button";
import {useRouter} from "next/router";
import {MyProduct} from "@/store/product/myProduct";
import {useSelector} from "react-redux";
import ProductImage from "@/components/product/ProductImage";
import ProductBrand from "@/components/product/ProductBrand";
import ProductPrice from "@/components/product/ProductPrice";
import ProductDescription from "@/components/product/ProductDescription";
import GridComponent, {StyledContainer, StyledContent, StyledSetion} from "@/components/common/GridComponent";

interface InfoProps{
    productData : MyProduct,
    children : React.ReactNode
}
function ProductInfo({severProductData,children}: InfoProps) {
    const util = new Util();
    const router = useRouter();
    const handleUpdateButtonClick = () => {
        const {productId}: number = router.query;
        router.push(`/admin/product/update/${productId}`)
    }

    const productRedux :MyProduct = useSelector(state => state.productRedux) ;
    return (
        <StyledContainer>
            <StyledContent isFull={true}>
                <StyledSetion isFull={true}>
                    <div className="first-section">
                        <GridComponent title={`📰${productRedux?.name}`}></GridComponent>
                        <div className="main-section">
                            <div className="flex">
                                <ProductImage></ProductImage>
                                <div className="section">
                                    <div>
                                        <span className="bold"><span>Product Info</span> <span className="gray">제품정보</span></span>
                                    </div>
                                    <ProductBrand/>
                                    <ProductPrice/>
                                    <div>
                                        <div className="bold">
                                            <SelectBox/>
                                            <Button variant="outlined" sx={{marginTop: 2}} onClick={handleUpdateButtonClick}>
                                                수정하기
                                            </Button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <ProductDescription/>
                        </div>
                    </div>
                </StyledSetion>
            </StyledContent>
        </StyledContainer>

        // <>
        //     <div className="flex">
        //         <div className="image">
        //             {/*{severProductData?.productImage ? severProductData.productImage : "image가 없습니다."}*/}
        //             {productRedux?.productImage ? productRedux.productImage : "image가 없습니다."}
        //         </div>
        //         <div className="section">
        //             <div>
        //                 <span className="bold"><span>Product Info</span> <span className="gray">제품정보</span></span>
        //             </div>
        //             <div>
        //                 <span className="bold"><span>브랜드 / 카테고리</span>
        //                     <span className="gray">
        //                         {/*{severProductData?.brand && severProductData.brand.name} / {severProductData?.category && severProductData.category.name}*/}
        //                         {productRedux?.brand && productRedux.brand.name} / {productRedux?.category && productRedux.category.name}
        //                     </span>
        //                 </span>
        //             </div>
        //             <div>
        //                 <span className="bold"><span>가격</span> <span
        //                     className="gray">{productRedux?.price && util.addCommasToNumber(productRedux.price)}</span></span>
        //                     {/*className="gray">{severProductData?.price && util.addCommasToNumber(severProductData.price)}</span></span>*/}
        //             </div>
        //             <div>
        //                 <div className="bold">
        //                     {
        //                         productRedux &&
        //                         // severProductData &&
        //                         // <SelectBox productComponents={severProductData.productComponents}></SelectBox>
        //                         <SelectBox productComponents={productRedux.productComponents}></SelectBox>
        //                     }
        //                     <Button variant="outlined" sx={{marginTop: 2}} onClick={handleUpdateButtonClick}>
        //                         수정하기
        //                     </Button>
        //                 </div>
        //             </div>
        //         </div>
        //     </div>
        //     <div>
        //         {
        //             productRedux?.description &&
        //             <div dangerouslySetInnerHTML={{__html: productRedux.description}}></div>
        //         }
        //     </div>
        // </>
    );
}
ProductInfo.ProductImage = ProductImage;
ProductInfo.ProductBrand = ProductBrand;
ProductInfo.ProductPrice = ProductPrice;
ProductInfo.ProductDescription = ProductDescription;

export default ProductInfo;