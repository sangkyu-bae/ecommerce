import React, {ReactNode, useEffect, useState} from 'react';
import {Button, Dialog, DialogActions, DialogContent, DialogTitle} from "@mui/material";
import {useModalActionContext, useModalDataContext} from "@/components/common/modal/ModalFilter";
import styled from "styled-components";

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
    const data = useModalDataContext();
    const actions = useModalActionContext();

    return(
        <StyledDialog open={data.isOpen} onClose={() =>actions.close()}>
            {children}
        </StyledDialog>
    );
}


function ModalBody(props) {
    const data = useModalDataContext();
    return (
        <>
            <DialogTitle>{data.title}</DialogTitle>
            <DialogContent>
                <div style={{ marginBottom: "16px" }}>
                    {data.info[0]?.title}
                </div>
                <div style={{ marginBottom: "16px" }}>
                    {data.info[0]?.content}
                </div>
            </DialogContent>
        </>
   );
}

function ModalFooter() {
    const actions = useModalActionContext();
    const data = useModalDataContext();

    return (
        <DialogActions>
            <StyledButton fullWidth onClick={actions.confirm}>{data.buttonTitle}</StyledButton>
        </DialogActions>
    )
}

TestModal.ModalBody = ModalBody;
TestModal.ModalFooter = ModalFooter;

export default TestModal;
