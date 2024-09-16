import React, {useEffect, useState} from 'react';
import {StyledContainer} from "@/components/common/GridComponent";
import {useMutation, useQuery} from "@tanstack/react-query";
import CardContent from "@mui/material/CardContent";
import {Backdrop, CardActions, Typography} from "@mui/material";
import Button from "@mui/material/Button";
import Box from "@mui/material/Box";
import Card from "@mui/material/Card";
import {CouponApi} from "@/shared/api/coupon/CouponApi";
import CardCouponComponent from "@/shared/ui/CardCouponComponent";
import {useEvent} from "@/shared/hook/useEvent";
// import {useAuth} from "@/shared/hook/useAuth";
import {getAccessToken} from "@/shared/api/cookie/Cookie";


function Coupon(props) {
    const [key,setKey] = useState<number>();
    const {data, isLoading, isError, error} = useQuery(
        ['event'],
        () => CouponApi.readAll(), {
            staleTime: 20000,
            enabled: true,
            onSuccess: data => {
                console.log("요청원료")
                console.log(data)
            },
            onError: e => {
                console.log(e.message)
            }
        }
    )
    // const {getAccessToken} = useAuth();
    const {messageData,changeContact} = useEvent({
        url :"http://localhost:8000/notification/queue-coupon",
        accessToken : getAccessToken(),
        hasContact : false
    });


    useEffect(()=>{
        if(!messageData?.statusType){
            return;
        }
        if (messageData.statusType.type == 1){

        }else if(messageData.statusType.type == 0){
            registerEventCoupon();
        }
    },[messageData])

    //캐싱필요없음
    const registerEventCoupon = async () =>{
        const res = await CouponApi.registerEventCoupon(key);
        //에러처리 필요
        if(res != "success"){
            return;
        }

        setOpen(true);
    }

    const onClick = (key : number) => {
        changeContact(true,`http://localhost:8000/notification/coupon/${key}`);
        setKey(key);
    }
    const [open, setOpen] = React.useState(false);

    return (
        <StyledContainer>
            <Backdrop
                sx={(theme) => ({ color: '#fff', zIndex: theme.zIndex.drawer + 1 })}
                open={open}
                onClick={()=>setOpen(false)}
            >
                <Box>
                    {messageData.sendMessage}
                </Box>
            </Backdrop>
            <Box sx={{width: "100%"}}>
                {
                    !isLoading && data.map((coupon) =>
                        <CardCouponComponent key={coupon.id}
                                             title={`${coupon.couponName} , 할인율 : ${coupon.salePercent}`}
                                             subTitle={`남은 수량 : ${coupon.quantity}`}
                                             btnTitle={'쿠폰 발급 하기'}
                                             clickEvent={()=>onClick(coupon.id)}
                        />
                    )
                }
            </Box>
        </StyledContainer>


    );
}

export default Coupon;
