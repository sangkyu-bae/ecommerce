import { useState} from "react";

interface modalInfo{
    data  : object,
    openEvent : () => void,
    closeEvent : () => void
}
export const useModal =({data ,openEvent,closeEvent}: modalInfo)=>{

    const [modalData,setModalData] = useState<object>(data);

    const openClickEvent = () =>{
        setModalData(prev => ({
            ...prev,
            isOpen: false
        }));
        openEvent();
    }

    const closeClickEvent = () =>{
        setModalData(prev => ({
            ...prev,
            isOpen: false
        }));
        closeEvent();
    }

    return {
        modalData,
        openClickEvent,
        closeClickEvent,

    }
}