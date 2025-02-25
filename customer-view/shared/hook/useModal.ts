import {useState} from "react";

interface modalInfo{
    isOpen : boolean
}
export const useModal =({isOpen}: modalInfo)=>{
    const [isModalOpen,setIsModalOpen] = useState<boolean>(isOpen);
}