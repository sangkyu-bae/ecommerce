import React, {ReactNode, useState} from 'react';
import {Button, Dialog, DialogActions, DialogContent, DialogTitle} from "@mui/material";
import {useModalActionContext, useModalDataContext} from "@/components/common/modal/ModalFilter";
import styled from "styled-components";
import {MyProduct} from "@/store/product/myProduct";

const StyledDialog = styled(Dialog)`
  .MuiPaper-root {
    border-radius: 12px;
    padding: 16px;
  }
`;

const StyledButton = styled(Button)`
  background-color: #333;
  color: white;
  &:hover {
    background-color: #555;
  }
`;

interface InfoProps {
    children: React.ReactNode
}


function TestModal({children} :InfoProps) {
    return(
        <StyledDialog open={true}>
            {children}
        </StyledDialog>
    );
}


function ModalBody(props) {
    const data = useModalDataContext();

    console.log(data);

    return (
        <>
            <DialogTitle>test</DialogTitle>
            <DialogContent>
                <div style={{ marginBottom: "16px" }}>
                    {data[0]?.title}
                </div>
                <div style={{ marginBottom: "16px" }}>
                    {data[0]?.content}
                </div>
            </DialogContent>
        </>
   );
}

function ModalFooter() {
    const actions = useModalActionContext();

    return (
        <DialogActions>
            <StyledButton fullWidth onClick={actions.close}>팝업 열기</StyledButton>
        </DialogActions>
    )
}

TestModal.ModalBody = ModalBody;
TestModal.ModalFooter = ModalFooter;

export default TestModal;
