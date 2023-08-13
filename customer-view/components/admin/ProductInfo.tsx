import React, {useEffect} from 'react';
import Util from "@/utils/CommonUtil";
import SelectBox from "@/components/common/SelectBox";
import Button from "@mui/material/Button";
import {useRouter} from "next/router";

function ProductInfo({productData}:{Product}) {
    const util = new Util();
    const router = useRouter();
    const handleUpdateButtonClick = () => {
        const {productId}: number = router.query;
        router.push(`/admin/product/update/${productId}`)
    }
    return (
        <>
            <div className="flex">
                <div className="image">
                    {productData?.productImage ? productData.productImage : "image가 없습니다."}
                </div>
                <div className="section">
                    <div>
                                        <span className="bold"><span>Product Info</span> <span
                                            className="gray">제품정보</span></span>
                    </div>
                    <div>
                                        <span className="bold"><span>브랜드 / 카테고리</span>
                                            <span className="gray">
                                                {productData?.brandDto && productData.brandDto.name} / {productData?.categoryDto && productData.categoryDto.name}
                                            </span>
                                        </span>
                    </div>
                    <div>
                        <span className="bold"><span>가격</span> <span className="gray">{productData?.price && util.addCommasToNumber(productData.price)}</span></span>
                    </div>
                    <div>
                        <div className="bold">
                            {
                                productData && <SelectBox colorProduct={productData.colorProductDtoList}></SelectBox>
                            }
                            <Button variant="outlined"  sx={{marginTop:2}} onClick={handleUpdateButtonClick}>
                                수정하기
                            </Button>
                        </div>
                    </div>
                </div>
            </div>
            <div>
                {
                    productData?.description &&
                    <div dangerouslySetInnerHTML={{__html: productData.description}}></div>
                }

            </div>
        </>
    );
}

export default ProductInfo;