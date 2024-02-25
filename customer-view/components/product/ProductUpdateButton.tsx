import React from 'react';
import SelectBox from "@/components/common/SelectBox";
import Button from "@mui/material/Button";

function ProductUpdateButton(props) {
    return (
        <div>
            <div className="bold">
                <SelectBox/>
                <Button variant="outlined" sx={{marginTop: 2}} onClick={handleUpdateButtonClick}>
                    수정하기
                </Button>
            </div>
        </div>
    );
}

export default ProductUpdateButton;