import React, {useEffect} from 'react';
import {StyledContainer} from "@/components/common/GridComponent";
import {useQuery} from "@tanstack/react-query";
import CardContent from "@mui/material/CardContent";
import {CardActions, Typography} from "@mui/material";
import Button from "@mui/material/Button";
import Box from "@mui/material/Box";
import Card from "@mui/material/Card";
import {CouponApi} from "@/shared/api/coupon/CouponApi";
import CardCouponComponent from "@/shared/ui/CardCouponComponent";


function Coupon(props) {
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

    useEffect(()=>{
        console.log(data)
    },[data])

    return (
        <StyledContainer>
            <Box sx={{width: "100%"}}>
                {
                    !isLoading && data.map((coupon) =>
                        <CardCouponComponent key={coupon.id}
                                             title={`${coupon.couponName} , 할인율 : ${coupon.salePercent}`}
                                             subTitle={`남은 수량 : ${coupon.quantity}`}
                                             btnTitle={'쿠폰 발급 하기'}
                        />
                    )
                }
                <Box sx={{minWidth: 500, maxWidth: '50%', margin: '2em auto', flex: 1, flexDirection: 'column'}}>
                    <Card variant="outlined">
                        <React.Fragment>
                            <CardContent>
                                <Typography gutterBottom sx={{color: 'text.secondary', fontSize: 14}}>
                                    Word of the Day
                                </Typography>
                                <Typography variant="h5" component="div">
                                    test
                                </Typography>
                                <Typography sx={{color: 'text.secondary', mb: 1.5}}>adjective</Typography>
                                <Typography variant="body2">
                                    well meaning and kindly.
                                    <br/>
                                    {'"a benevolent smile"'}
                                </Typography>
                            </CardContent>
                            <CardActions>
                                <Button size="small">Learn More</Button>
                            </CardActions>
                        </React.Fragment>
                    </Card>
                </Box>

            </Box>


        </StyledContainer>


    );
}

export default Coupon;
