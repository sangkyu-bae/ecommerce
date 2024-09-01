import {useEffect, useState} from "react";
import { EventSourcePolyfill } from "event-source-polyfill";
export const useEvent = (url: string, accessToken: string) => {
    const [messageData,setMessageData] = useState({});

    useEffect(()=>{
        const eventSource = new EventSourcePolyfill(url,
            {
                headers: {
                    Authorization: accessToken,
                    Connetction: 'keep-alive',
                    Accept: 'text/event-stream',
                },
                withCredentials: true
            }
        );

        eventSource.onmessage=async (e) =>{
            const res = await e.data;
            const parseData : MessageData = JSON.parse(res);

            // setMessageData(parseData);

            console.log(parseData)
            if(parseData.eventType.type == 1){
                eventSource.close()
            }

        }

        eventSource.onerror=(e)=>{
            eventSource.close();

            setMessageData({
                messageType : "error",
                message : "error 발생"
            });
        }
    },[])


    return{
        messageData
    }
}