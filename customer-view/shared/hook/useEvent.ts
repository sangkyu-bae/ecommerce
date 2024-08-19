import {useState} from "react";

export const useEvent = (url: string, accessToken: string) => {
    const [messageData,setMessageData] = useState({});

    const eventSource = new EventSource(url,
        {
            headers: {
                Authorization: accessToken
            },
            withCredentials: true
        }
    );

    eventSource.onmessage=async (e) =>{
        const res = await e.data;
        const parseData : MessageData = JSON.parse(res);

        setMessageData(parseData);

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

    return{
        messageData
    }
}