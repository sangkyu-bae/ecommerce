import React, {createContext, useContext, useMemo} from 'react';
import Modal from "@/components/common/modal/modal";
import {useProductActionContext, useProductValueContext} from "@/components/product/ProductInfo";
import {DialogActions} from "@mui/material";

interface modalType {
    type: string,
    data:[{
        title: string,
        content :string
    }],
    confirmEvent :() => void,
    closeEvent : () => void
}
const MODAL_COMPONENTS: Record<string, () => JSX.Element> = {
    confirm: () => {
        const data = useModalDataContext();
        const actions = useModalActionContext();

        return (
            <Modal title={data[0]?.title} onClose={actions.close}>
                <Modal.Body>
                    <p>{data[0]?.content}</p>
                    <p>dddd</p>
                </Modal.Body>
                <Modal.Footer>
                    <DialogActions>
                        <StyledButton fullWidth onClick={actions.close}>close</StyledButton>
                    </DialogActions>
                </Modal.Footer>
            </Modal>
        );
    },
    alert: () => {
        const data = useModalDataContext();
        const actions = useModalActionContext();

        return (
            <Modal title={data[0]?.title} onClose={actions.close}>
                <Modal.Body>
                    <p>{data[0]?.content}</p>
                </Modal.Body>
                <Modal.Footer>
                    <button onClick={actions.close}>OK</button>
                </Modal.Footer>
            </Modal>
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

    if (data.length < 1) {
        return <></>
    }
    const action = useMemo(
        ()=>({
                confirm() {
                    confirmEvent()
                },
                close(){
                    closeEvent();
                }
        }),[]
    );

    switch (type) {
        case 'tt':
            return <Modal title="t" content="eteste"></Modal>;
    }

    const ModalComponent = MODAL_COMPONENTS[type]

    return (
        <ModalDataContext.Provider value={data}>
            <ModalActionContext.Provider value={action}>
                <ModalComponent />;
            </ModalActionContext.Provider>
        </ModalDataContext.Provider>
    )


}

export default ModalFilter;