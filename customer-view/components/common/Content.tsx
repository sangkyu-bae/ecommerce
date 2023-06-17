import React from 'react';
import styled from "styled-components";

function Content(props) {
    const StyledContent = styled.div`
        flex : 0.87;
        background-color : #FFFAF0;
    `
    return (
        <StyledContent>
            <div>상품 등록</div>
            <div>
                <label>상품명</label>
                <input type="text"/>
            </div>
            <div>
                <label>가격</label>
                <input type="number"/>
            </div>
            <div>
                <label>상품 설명</label>
                <textarea></textarea>
            </div>

        </StyledContent>
    );
}

export default Content;