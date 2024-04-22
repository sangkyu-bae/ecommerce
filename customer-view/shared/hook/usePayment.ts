import React, {useEffect, useState} from 'react';
import {MyProduct, OrderProduct} from "@/store/product/myProduct";

function UsePayment(orderProducts: OrderProduct[]) {

    const [payment,setPayment] = useState({
        totalPayment : 0,
        couponPayment : 0
    });

    useEffect(()=>{
        const totalPayment = orderProducts.reduce(function (acc,cur,idx){
            return acc + (cur.product.price * cur.quantity)
        },0)

        setPayment({
            ...payment,
            totalPayment: totalPayment
        })

    },[orderProducts])

    const applyCoupon = (discount:number) =>{

        const discountPayment = payment.totalPayment - (payment.totalPayment * discount);
        setPayment({
            ...payment,
            couponPayment: discountPayment
        })
    }

    return {
        payment : payment,
        applyCoupon : applyCoupon
    }


}

export default UsePayment;