import React, {createContext, useContext, useMemo} from 'react';
import TestModal from "@/components/common/modal/TestModal";
import {useModal} from "@/shared/hook/useModal";

interface modalType {
    type: string,
    data:{
                title  : string,
                buttonTitle : string,
                info : [{
                    title: string,
                    content :string
                }],
                isOpen : boolean
        }
    ,
    confirmEvent :() => void,
    closeEvent : () => void
}
const MODAL_COMPONENTS: Record<string, () => JSX.Element> = {
    confirm: () => {
        return (
            <TestModal>
                <TestModal.ModalBody/>
                <TestModal.ModalFooter/>
            </TestModal>
        );
    },
    alert: () => {
        return (
            <TestModal>
                <TestModal.ModalBody/>
                <TestModal.ModalFooter/>
            </TestModal>
        );
    },
};

const ModalDataContext = createContext();
const ModalActionContext = createContext();

export function useModalDataContext(){
    return useContext(ModalDataContext);
}

export function useModalActionContext(){
    return useContext(ModalActionContext);
}
function ModalFilter({type, data,confirmEvent,closeEvent}: modalType) {

    if (data.info.length < 1) {
        return <></>
    }

    const { modalData,openClickEvent,closeClickEvent } = useModal({
        data  : data,
        openEvent: confirmEvent,
        closeEvent: closeEvent
    });




    const action = useMemo(
        ()=>({
                confirm() {
                    openClickEvent()
                },
                close(){
                    closeClickEvent();
                }
        }),[confirmEvent,closeEvent]
    );




    const ModalComponent = MODAL_COMPONENTS[type]
    return (
        <ModalDataContext.Provider value={modalData}>
            <ModalActionContext.Provider value={action}>
                <ModalComponent />;
            </ModalActionContext.Provider>
        </ModalDataContext.Provider>
    )


}

export default ModalFilter;