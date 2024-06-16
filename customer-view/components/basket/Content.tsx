import React, {useState} from 'react';
import {useBasket} from "@/shared/hook/useBasket";
const initialProducts = [
    { id: 1, name: 'Product Name 1', price: 10, image: 'https://via.placeholder.com/50', quantity: 1 },
    { id: 2, name: 'Product Name 2', price: 15, image: 'https://via.placeholder.com/50', quantity: 1 },
    { id: 3, name: 'Product Name 3', price: 20, image: 'https://via.placeholder.com/50', quantity: 1 },
];

function Content(props) {
    const {data,isLoading,error} = useBasket(true, false);
    const [products, setProducts] = useState(initialProducts);

    const handleQuantityChange = (id: number, quantity: number) => {
        setProducts(products.map(product =>
            product.id === id ? { ...product, quantity } : product
        ));
    };

    const calculateTotal = () => {
        return products.reduce((total, product) => total + product.price * product.quantity, 0);
    };
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
                {basketProducts.map(product => (
                    <tr key={product.id}>
                        <td>
                            <img src='https://via.placeholder.com/50' alt="Product" />
                            {product.productName}
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
                <strong>Total: ${calculateTotal().toFixed(2)}</strong>
            </div>
            <div className="actions">
                <button>Proceed to Checkout</button>
            </div>
        </div>
    );
}

export default Content;
