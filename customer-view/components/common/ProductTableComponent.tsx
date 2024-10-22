import React from 'react';

interface product{
    id:number,
    size:number,
    colorName:string,
    quantity :number,
    productName : string,
    price : number
    children : React.ReactNode
}
function ProductTableComponent({id,size,colorName,quantity,children,productName,price} : product) {
    return (
        <tr>
            <td className="normal-td flex">
                <>
                {children}
                </>
                <img src='https://via.placeholder.com/50' alt="Product"/>
                <div>
                    <span className="bold">{productName}</span><br/>
                    <span>{size} /{colorName}</span>
                </div>

            </td>
            <td className="normal-td">{productName} / {size}/ {colorName}/ {quantity}</td>
            <td className="normal-td">{price.toLocaleString()} 원</td>
            <td className="normal-td">무료</td>
        </tr>
    );
}

export default ProductTableComponent;
