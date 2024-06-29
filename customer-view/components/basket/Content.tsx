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

function Content(props) {
    const {
        isLoading,
        error,
        baskets,
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
            dispatch(initProduct());

            baskets.forEach(basket => {
                const order = {
                    productId: basket.productId,
                    color: {
                        id:basket.productComponentEntityVoList[0].color.id,
                        name: basket.productComponentEntityVoList[0].color.name
                    },
                    size: {
                        id:basket.productComponentEntityVoList[0].sizes.id,
                        name: basket.productComponentEntityVoList[0].sizes.name
                    }
                };
                dispatch(setProduct(basket, false));
                dispatch(basketAddBuyProduct(order.color, order.size, order.productId));
                dispatch(basketQuantitySetting(basket.productId, basket.colorName,basket.size,basket.productQuantity))
            });
        }
    }, [baskets])

    const {isOrderData, product, selectProducts} = useSelector(state => state.productRedux);

    useEffect(() => {
        console.log(product)
    }, [product])
    useEffect(()=>{
        console.log(selectProducts)
    },[selectProducts])
    return (
        <div className="container">
            <h1>Shopping Cart</h1>
            <table className="cart">
                <thead>
                <tr>
                    <th>Product</th>
                    <th>Price</th>
                    <th>Quantity</th>
                    <th>Total</th>
                </tr>
                </thead>
                <tbody>
                {baskets.map(product => (
                    <tr key={product.id}>
                        <td>
                            <img src='https://via.placeholder.com/50' alt="Product"/>
                            {product.productName} / {product.size} /{product.colorName}
                        </td>
                        <td>${product.price.toFixed(2)}</td>
                        <td className="quantity">
                            <input
                                type="number"
                                value={product.productQuantity}
                                min="1"
                                onChange={(e) => handleQuantityChange(product.id, Number(e.target.value))}
                            />
                        </td>
                        <td>${(product.price * product.productQuantity).toFixed(2)}</td>
                    </tr>
                ))}
                </tbody>
            </table>
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
