import React, {useEffect} from 'react';
import {useDispatch, useSelector} from "react-redux";
import GridComponent, {
    StyledContainer,
    StyledContent,
    StyledOrderBox,
    StyledSetion
} from "@/components/common/GridComponent";

import OrderInfoBox from "@/components/order/OrderInfoBox";
import Button from "@mui/material/Button";
import OrderInfoContainer from "@/components/order/OrderInfoContainer";
import TextField from "@mui/material/TextField";
import Select from "@mui/material/Select";
import MenuItem from "@mui/material/MenuItem";
import useFormHook from "@/shared/hook/useFormHook";
import {orderValidation} from "@/shared/utils/validation/orderValidation";
import {OrderApi} from "@/shared/api/order/orderApi";
import {OrderProduct} from "@/store/product/myProduct";


function Order(props) {
    const dispatch = useDispatch();
    const products : OrderProduct[]= useSelector(state => state.productRedux);

    const totalPrice = products.reduce((total,product)=>total + (product.selectPrice * product.quantity),0);

    const {register, handleSubmit, errors} = useFormHook({
        initData:null,
        onSubmit:OrderApi.register,
        validation:orderValidation,
        // dataParsingEvent:dataParsingEvent
    });

    const validation = orderValidation;
    return (
        <StyledContainer>
            <StyledContent isFull={true}>
                <StyledSetion isFull={true}>
                    <div className="first-section">
                        <GridComponent title={`주문하기`}>
                            <StyledOrderBox>
                                <form onSubmit={handleSubmit}>
                                    배송정보
                                    <div className="flex-box">
                                        <div>이름 / 연락처</div>
                                        <div>홍길동</div>
                                        <TextField margin="normal"
                                                   required
                                                   id="phone"
                                                   label="연락처"
                                                   name="phone"
                                                   {...register("phone",{
                                                       ...validation.phone
                                                   })}
                                                   error = {Boolean(errors.phone)}
                                                   helperText={errors.phone?.message}
                                                   autoFocus/>
                                    </div>
                                    <div className="flex-box">
                                        <div>주소</div>
                                        <TextField margin="normal"
                                                   required
                                                   id="address"
                                                   label="배송지"
                                                   name="address"
                                                   {...register("address",{
                                                        ...validation.address
                                                   })}
                                                   error = {Boolean(errors.address)}
                                                   helperText={errors.address?.message}
                                                   autoFocus/>
                                    </div>
                                    <div className="flex-box">
                                        <div> 배송 요청사항</div>
                                        <Select
                                            sx={{mt: 5}}
                                            labelId="size-select"
                                            id="size-select"
                                            label="Size"
                                            name="size"
                                        >
                                            <MenuItem value={0}>옵션 선택</MenuItem>
                                        </Select>
                                    </div>
                                    <OrderInfoContainer/>
                                    <Button
                                        variant="contained"
                                        sx={{mt: 2}}
                                        type="submit"
                                    >{totalPrice.toLocaleString('en-US')} 원
                                        결제하기
                                    </Button>
                                </form>

                            </StyledOrderBox>
                        </GridComponent>
                    </div>
                </StyledSetion>
            </StyledContent>
        </StyledContainer>
    );
}

export default Order;