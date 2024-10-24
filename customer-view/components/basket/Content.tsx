import React, {useEffect, useState} from 'react';
import {useBasket} from "@/shared/hook/useBasket";
import {useDispatch, useSelector} from "react-redux";
import {
    addBuyProduct,
    basketAddBuyProduct,
    basketQuantitySetting,
    initProduct,
    setProduct
} from "@/store/product/productRedux";
import {useRouter} from "next/router";
import {BoxContainer} from "@/components/common/GridComponent";
import Checkbox from "@mui/material/Checkbox";
import Button from "@mui/material/Button";
import ProductTableComponent from "@/components/common/ProductTableComponent";
import {OrderProduct} from "@/store/product/myProduct";

function Content(props) {
    const {queryData} = useBasket(true);
    const {data} = queryData;
    const [baskets,setBaskets] = useState<Basket[]>([]);
    const [allChecked,setAllChecked] = useState<boolean>(false);
    const dispatch = useDispatch();
    const router = useRouter()

    const totalAmount = baskets.filter(basket=>basket.check)
        .reduce((total, basket) => total + basket.price * basket.productQuantity, 0).toLocaleString('ko-KR');

    const orderProduct = () => {

        const filterBaskets : OrderProduct[]= baskets
            .filter(basket => basket.check)
            .map(filterBasket => ({
                productId: filterBasket.productId,
                color: filterBasket.colorName,
                size: filterBasket.size,
                quantity: filterBasket.productQuantity,
                selectPrice: filterBasket.price,
                productName : filterBasket.productName
            }));
        if(filterBaskets.length < 1){
            alert("구매할 상품을 선택해주세요.")
            return;
        }
        dispatch((initProduct()))
        dispatch(setProduct(filterBaskets));
        router.push("/order");
    }

    useEffect(() => {
        if (data) {
         setBaskets(data);
        }
    }, [data])


    useEffect(()=>{
        setBaskets(prevBaskets =>
            prevBaskets.map(basket => ({
                ...basket,
                check: allChecked
            }))
        );
    },[allChecked])

    const onChangeCheck =(value : 'all'|number)=>{
        if(value == 'all'){
            setAllChecked(!allChecked);
        }else{
            setBaskets(prevBaskets =>
                prevBaskets.map(basket =>
                    basket.id === value
                        ? { ...basket, check: !basket.check }
                        : basket
                )
            );
        }
    }
    return (
        <div className="container">
            <BoxContainer >
                <table className="full position-r">
                    <thead className="t-head full">
                    <tr className="full">
                        <th className="normal-b position-r ">
                            <Checkbox style={{position:"absolute",left:0 ,top:'13%'}}
                                      onClick={()=>onChangeCheck('all')}
                                      checked={allChecked}
                                      defaultChecked={false}
                            />
                            <span>상품정보</span>
                        </th>
                        <th className="normal-th">옵션</th>
                        <th className="normal-th">상품금액</th>
                        <th className="normal-th">배송비</th>
                    </tr>
                    </thead>

                    <div className="full border-b position-a">

                    </div>
                    <div style={{height:"2em"}}>

                    </div>
                    <tbody>
                    {baskets.map(basket => (
                        <ProductTableComponent
                            key={basket.id}
                            id={basket.id}
                            size ={basket.size}
                            colorName={basket.colorName}
                            quantity={basket.productQuantity}
                            productName={basket.productName}
                            price = {basket.price}

                        >
                            <Checkbox
                                defaultChecked={false}
                                checked={basket.check}
                                onClick={()=> onChangeCheck(basket.id)}
                            />
                        </ProductTableComponent>
                    ))}
                    </tbody>
                    <div>


                    </div>

                </table>
            </BoxContainer>
            <div style={{marginTop:'50px'}}>
                <div className="total" style={{marginBottom:'15px'}}>
                    <strong>Total: {totalAmount}원</strong>
                </div>
                <div className="actions">
                    <Button onClick={orderProduct} variant="outlined">구매하기</Button>
                </div>
            </div>

        </div>
    );
}

export default Content;
