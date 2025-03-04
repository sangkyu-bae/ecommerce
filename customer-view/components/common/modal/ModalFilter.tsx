import React from 'react';
import Modal from "@/components/common/modal/modal";

interface ttss {
    type: string,
    data: object[]
}

function ModalFilter({type, data}: ttss) {

    if (data.length < 1) {
        return <></>
    }

    switch (type) {
        case 'tt':
            return <Modal title="t" content="eteste"></Modal>;
    }
}

export default ModalFilter;