import React, {useEffect} from 'react';
import {useEvent} from "@/shared/hook/useEvent";
import {useAuth} from "@/shared/hook/useAuth";
import { EventSourcePolyfill } from "event-source-polyfill";
function Test(props) {
    const {getAccessToken} = useAuth();
    const {messageData} = useEvent("http://localhost:8000/notification/queue-coupon",getAccessToken())


    return (
        <div></div>
    );
}

export default Test;