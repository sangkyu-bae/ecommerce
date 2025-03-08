import React, {useEffect, useState} from 'react';
import GridComponent, {StyledContent, StyledSetion} from "@/components/common/styles/GridComponent";
import Box from "@mui/material/Box";
import ProductCardComponent from "@/components/common/ProductCardComponent";
import Link from "next/link";
import {useQueryClient} from "@tanstack/react-query";
import {QUERY_KEYS} from "@/shared/constants/queryKeys";
import ModalFilter from "@/components/common/modal/ModalFilter";
import {useEventCouponService} from "@/shared/hook/useEventCouponService";
function MainSection() {

    const queryClient = useQueryClient();

    const couponData = queryClient.getQueryData(QUERY_KEYS.COUPON.key);
    const rankData = queryClient.getQueryData(["clickRank"]);

    const modalData = couponData.map(coupon =>{
        return {
            title : coupon.couponName,
            content : `이벤트 쿠폰 할인율 ${ coupon.salePercent}% 쿠폰 남은 수량 ${coupon.quantity}`
        }
    })

    const {goPage,close} = useEventCouponService();

    return (
        <StyledContent isFull={true}>
            <StyledSetion isFull={true}>
                <GridComponent title={"💎베스트 상품"}/>
                <Box  sx={{width:'100%'}} >
                    <Box sx={{display:'flex', margin:'0 auto', width:'65%', flexWrap: 'wrap'}}>
                        {
                            rankData.map(product=>
                                <Link  key={product.productId} href={`/product/${product.productId}`} style={{
                                    textDecoration: 'none',
                                    color: 'inherit',
                                    marginLeft:'4em',
                                    marginTop:'2em'
                                }}>
                                    <ProductCardComponent
                                        image={product.productImage}
                                        productName={product.productName}
                                        minWidth={'330px'}
                                    />
                                </Link>

                            )
                        }
                    </Box>
                </Box>

            </StyledSetion>
            <ModalFilter type = {"tt"} data= {modalData} confirmEvent={goPage} closeEvent={close} />

        </StyledContent>

    );
}

export default MainSection;