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

function Content(props) {
    const {
        isLoading,
        error,
        baskets,
        setBaskets,
        handleQuantityChange,
        calculateTotal
    } = useBasket(true, true, true);

    const dispatch = useDispatch();
    const router = useRouter()
    const orderProduct = () => {
        router.push("/order");
    }
    useEffect(() => {
        if (baskets) {
            console.log(baskets)
            // dispatch(initProduct());
            // baskets.forEach(basket => {
            //     const order = {
            //         productId: basket.productId,
            //         color: {
            //             id:basket.productComponentEntityVoList[0].color.id,
            //             name: basket.productComponentEntityVoList[0].color.name
            //         },
            //         size: {
            //             id:basket.productComponentEntityVoList[0].sizes.id,
            //             name: basket.productComponentEntityVoList[0].sizes.name
            //         }
            //     };
            //     dispatch(setProduct(basket, false));
            //     dispatch(basketAddBuyProduct(order.color, order.size, order.productId));
            //     dispatch(basketQuantitySetting(basket.productId, basket.colorName,basket.size,basket.productQuantity))
            // });
        }
    }, [baskets])

    const {isOrderData, product, selectProducts} = useSelector(state => state.productRedux);
    const [allChecked,setAllChecked] = useState<boolean>(false);

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
                        <tr key={basket.id}>
                            <td className="normal-td flex">
                                <Checkbox
                                    defaultChecked={false}
                                    checked={basket.check}
                                    onClick={()=> onChangeCheck(basket.id)}
                                />
                                <img src='https://via.placeholder.com/50' alt="Product"/>
                                <div>
                                    <span className="bold">{basket.productName}</span><br/>
                                    <span>{basket.size} /{basket.colorName}</span>
                                </div>

                            </td>
                            <td className="normal-td">{basket.productName} / {basket.size}/ {basket.colorName}/ {basket.productQuantity
                            }</td>
                            <td className="normal-td">{basket.price.toLocaleString()} 원</td>
                            <td className="normal-td">무료</td>
                            {/*<td className="quantity normal-td">*/}
                            {/*    <input*/}
                            {/*        type="number"*/}
                            {/*        value={product.productQuantity}*/}
                            {/*        min="1"*/}
                            {/*        onChange={(e) => handleQuantityChange(product.id, Number(e.target.value))}*/}
                            {/*    />*/}
                            {/*</td>*/}
                            {/*<td className="normal-td">${(product.price * product.productQuantity).toFixed(2)}</td>*/}
                        </tr>
                    ))}
                    </tbody>
                    <div>


                    </div>

                </table>
            </BoxContainer>

            <div className="total">
                <strong>Total: ${calculateTotal()}</strong>
            </div>
            <div className="actions">
                <button onClick={orderProduct}>Proceed to Checkout</button>
            </div>
        </div>
    );
}

export default Content;
