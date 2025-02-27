import { useState } from "react";
import { Dialog, DialogTitle, DialogContent, DialogActions, Button } from "@mui/material";
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

interface Modal{
    title :string,
    content : string
}

function Modal({title,content} : Modal) {
    const [isModalOpen, setModalOpen] = useState(true);
    const [isPopupOpen, setPopupOpen] = useState(false);
  
    return (
      <>
        {/* 모달 */}
        <StyledDialog open={isModalOpen} onClose={() => setModalOpen(false)}>
          <DialogTitle>{title}</DialogTitle>
          <DialogContent>
            <div style={{ marginBottom: "16px" }}>
                {title}
              {/* <label>대표자 이름</label>
              <input type="text" defaultValue="이상원" style={{ width: "100%", padding: "8px", marginTop: "4px", border: "1px solid #ccc", borderRadius: "8px" }} /> */}
            </div>
            <div style={{ marginBottom: "16px" }}>
                {content}
              {/* <label>휴대폰 번호</label>
              <input type="text" defaultValue="01031" style={{ width: "100%", padding: "8px", marginTop: "4px", border: "1px solid #ccc", borderRadius: "8px" }} /> */}
            </div>
          </DialogContent>
          <DialogActions>
            <StyledButton fullWidth onClick={() => setPopupOpen(true)}>팝업 열기</StyledButton>
          </DialogActions>
        </StyledDialog>
  
        {/* 팝업 */}
        <StyledDialog open={isPopupOpen} onClose={() => setPopupOpen(false)}>
          <DialogTitle>테스트 팝업입니다.</DialogTitle>
          <DialogActions>
            <StyledButton fullWidth onClick={() => setPopupOpen(false)}>팝업 닫기</StyledButton>
          </DialogActions>
        </StyledDialog>
      </>
    );
}   
export default Modal;