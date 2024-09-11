import {useEffect, useState} from "react";
import { EventSourcePolyfill } from "event-source-polyfill";
type event = {
    url : string,
    accessToken : string,
    hasContact : boolean
}
export const useEvent = ({url,accessToken,hasContact} : event) => {
    const [messageData,setMessageData] = useState({});
    const [isContact,setIsContact] = useState<boolean>(hasContact);
    const [sendUrl, setSendUrl] = useState<string>(url);
    useEffect(()=>{
        if(!isContact){
            return;
        }
        const eventSource = new EventSourcePolyfill(sendUrl,
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
    },[isContact])

    const changeContact = (isContact : boolean,url:string) =>{
        setSendUrl(url);
        setIsContact(isContact);
    }



    return{
        messageData,
        changeContact
    }
}