import React, {useEffect, useState} from 'react';

import {ErrorBoundary} from "@/components/error/ErrorBoundary";
import OrderFetcher from "@/shared/api/order/OrderFetcher";
import MyOrderContainer from "@/components/order/MyOrderContainer";


function List(props) {
    return (
        <ErrorBoundary>
            <OrderFetcher>
                <MyOrderContainer/>
            </OrderFetcher>
        </ErrorBoundary>
    )
}

export default List;
