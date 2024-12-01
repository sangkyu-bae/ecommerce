import React from 'react';
import {ErrorBoundary} from "@/components/error/ErrorBoundary";
import BasketFetcher from "@/shared/api/basket/BasketFetcher";
import BasketContainer from "@/components/basket/BasketContainer";

function Basket(props) {

    return (
        <ErrorBoundary>
            <BasketFetcher>
                <BasketContainer/>
            </BasketFetcher>
        </ErrorBoundary>
    );
}

export default Basket;
