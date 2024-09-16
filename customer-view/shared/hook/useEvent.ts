import {useEffect, useState} from "react";
import {EventSourcePolyfill} from "event-source-polyfill";

type event = {
    url: string,
    accessToken: string,
    hasContact: boolean
}
export const useEvent = ({url, accessToken, hasContact}: event) => {
    const [messageData, setMessageData] = useState<MessageData>({});
    const [isContact, setIsContact] = useState<boolean>(hasContact);
    const [sendUrl, setSendUrl] = useState<string>(url);
    useEffect(() => {
        if (!isContact) {
            return;
        }
        const eventSource = new EventSourcePolyfill(sendUrl,
            {
                headers: {
                    Authorization: accessToken,
                    Connetction: 'keep-alive',
                    Accept: 'text/event-stream',
                },
                withCredentials: true,
                heartbeatTimeout: 8640000,
            }
        );

        eventSource.onmessage = async (e) => {
            const res = await e.data;
            const parseData: MessageData = JSON.parse(res);

            // setMessageData(parseData);

            console.log(parseData)
            if (parseData.statusType.type == 1) {
                eventSource.close();
                return;
            }

            setMessageData(parseData)

        }

        eventSource.onerror = (e) => {
            eventSource.close();

            setMessageData({
                eventType: {
                    type: 4,
                    name: "error"
                },
                sendMessage: "error 발생",
                statusType: {
                    type: 4,
                    name: "error"
                }
            });
        }
    }, [isContact])

    const changeContact = (isContact: boolean, url: string) => {
        setSendUrl(url);
        setIsContact(isContact);
    }

    return {
        messageData,
        changeContact
    }
}