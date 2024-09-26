import React, {useEffect, useState} from 'react';
import {StyledContainer} from "@/components/common/GridComponent";
import {useMutation, useQuery} from "@tanstack/react-query";
import {Backdrop} from "@mui/material";
import Button from "@mui/material/Button";
import Box from "@mui/material/Box";
import {CouponApi} from "@/shared/api/coupon/CouponApi";
import CardCouponComponent from "@/shared/ui/CardCouponComponent";
import {useEvent} from "@/shared/hook/useEvent";
import {getAccessToken} from "@/shared/api/cookie/Cookie";


function Coupon(props) {
    const [key,setKey] = useState<number>();
    const [open, setOpen] = React.useState(false);

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
    const {messageData,changeContact,setIsContact} = useEvent({
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

        console.log(res)
        //에러처리 필요
        if(res?.error){
            alert(res.message)
            setIsContact(false)
            return;
        }

        setOpen(true);
    }

    const onClick = (key : number) => {
        changeContact(true,`http://localhost:8000/notification/coupon/${key}`);
        setKey(key);
    }

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
                        >
                            {
                                coupon.issued ?
                                <Box>
                                    이미 발급이 완료 되었습니다.
                                </Box>
                                :
                                <Button sx={{margin: "0 auto"}}
                                        variant="contained"
                                        onClick={() => onClick(coupon.id)}
                                >쿠폰 발급 하기</Button>
                            }
                        </CardCouponComponent>
                    )
                }
            </Box>
        </StyledContainer>


    );
}

export default Coupon;
