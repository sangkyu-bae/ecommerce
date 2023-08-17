import React, {useEffect} from 'react';
import Util from "@/utils/CommonUtil";
import SelectBox from "@/components/common/SelectBox";
import Button from "@mui/material/Button";
import {useRouter} from "next/router";

function ProductInfo({severProductData}:{Product}) {
    const util = new Util();
    const router = useRouter();
    const handleUpdateButtonClick = () => {
        const {productId}: number = router.query;
        router.push(`/admin/product/update/${productId}`)
    }
    useEffect(()=>{
        console.log((severProductData))
    },[])
    return (
        <>
            <div className="flex">
                <div className="image">
                    {severProductData?.productImage ? severProductData.productImage : "image가 없습니다."}
                </div>
                <div className="section">
                    <div>
                                        <span className="bold"><span>Product Info</span> <span
                                            className="gray">제품정보</span></span>
                    </div>
                    <div>
                                        <span className="bold"><span>브랜드 / 카테고리</span>
                                            <span className="gray">
                                                {severProductData?.brand && severProductData.brand.name} / {severProductData?.category && severProductData.category.name}
                                            </span>
                                        </span>
                    </div>
                    <div>
                        <span className="bold"><span>가격</span> <span className="gray">{severProductData?.price && util.addCommasToNumber(severProductData.price)}</span></span>
                    </div>
                    <div>
                        <div className="bold">
                            {
                                severProductData && <SelectBox colorProduct={severProductData.colorDataList}></SelectBox>
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
                    severProductData?.description &&
                    <div dangerouslySetInnerHTML={{__html: severProductData.description}}></div>
                }

            </div>
        </>
    );
}

export default ProductInfo;