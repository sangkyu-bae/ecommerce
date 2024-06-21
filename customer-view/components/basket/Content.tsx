import React, {useEffect, useState} from 'react';
import {useBasket} from "@/shared/hook/useBasket";
import {useDispatch, useSelector} from "react-redux";
import {addBuyProduct, initProduct, setProduct} from "@/store/product/productRedux";

function Content(props) {
    const {
        isLoading,
        error,
        baskets,
        handleQuantityChange,
        calculateTotal
    } = useBasket(true, true, true);

    const dispatch = useDispatch();
    useEffect(() => {
        if (baskets) {
            dispatch(initProduct());

            baskets.forEach(basket => {
                const order = {
                    productId: basket.productId,
                    color: {
                        name: basket.colorName
                    },
                    size: {
                        name: basket.size
                    }
                };

                // dispatch the setProduct action
                dispatch(setProduct(basket, false));

                // dispatch the addBuyProduct action
                dispatch(addBuyProduct(order.color, order.size, order.productId));
            });

            // dispatch(setProduct(baskets[0], false))
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
                <button>Proceed to Checkout</button>
            </div>
        </div>
    );
}

export default Content;
