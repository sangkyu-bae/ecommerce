import React, {useEffect, useRef, useState} from 'react';
import SideBar from "@/components/admin/sideBar";
import GridComponent, {StyledContent, StyledMenu, StyledSetion} from "@/components/common/GridComponent";
import TextField from "@mui/material/TextField";
import Input from "@/components/admin/Input";
import Button from "@mui/material/Button";
import SendIcon from "@mui/icons-material/Send";
import dynamic from "next/dynamic";
import styled from "styled-components";
import Validation from "@/utils/Validation";
import useUploadProduct from "@/shared/hook/useUploadProduct";
import SizeContainers from "@/components/admin/SizeContainers";


const NoSsrEditor = dynamic(() => import('@/components/common/' + 'ReactEdit'), {ssr: false});
const StyledContainer = styled.div`
        display : flex;
        width : 100%;
        height : 100vh;
    `
type ProductAdmin = {
    title: string,
    buttonTitle: string,
    initProductData: Product
}

function UploadProductComponents({title, buttonTitle, initProductData}: ProductAdmin) {
    const refs = useRef<any>(null);
    const {
        register,
        handleSubmit,
        errors,
        onChangeDescription,
        productInfo,
        isLoading,
        addProductComponent,
        updateProductComponent,
        removeProductComponent,
        getValues
    } = useUploadProduct({
        productData: initProductData,
        ref: refs
    });

    const validation = Validation;

    return (
        <StyledContainer>
            <SideBar type={'admin'}></SideBar>
            <StyledContent>
                <StyledMenu></StyledMenu>
                <StyledSetion isproduct={false}>
                    {
                        !isLoading &&
                        <div className="main-section">
                            <form onSubmit={handleSubmit}>
                                <GridComponent title={title}></GridComponent>
                                <TextField
                                    margin="normal"
                                    required
                                    id="name"
                                    label="상품명"
                                    name="name"
                                    fullWidth
                                    autoFocus
                                    {...register("name", {
                                        ...validation.name
                                    })}
                                    error={Boolean(errors.name)}
                                    helperText={errors.name?.message}
                                    defaultValue={initProductData.name}
                                />
                                <TextField
                                    type="number"
                                    margin="normal"
                                    required
                                    id="price"
                                    label="가격(원)"
                                    {...register("price", {
                                        ...validation.price
                                    })}
                                    error={Boolean(errors.price)}
                                    helperText={errors.price?.message}
                                    name="price"
                                    style={{width: '32%'}}
                                    defaultValue={initProductData.price}
                                    autoFocus
                                />

                                <Input names={productInfo[1].data}
                                       title="category"
                                       width={32}
                                       marginLeft={2}
                                       register={register}
                                       errors={errors}
                                       value={initProductData.category.id}
                                />

                                <Input names={productInfo[0].data}
                                       title="brand"
                                       width={32}
                                       marginLeft={2}
                                       register={register}
                                       errors={errors}
                                       value={initProductData.brand.id}
                                />

                                {
                                    getValues().productComponents.length > 0 &&
                                    getValues().productComponents.map((component,index) =>
                                            <SizeContainers
                                                key={index}
                                                // sizes={productPieceData.sizes}
                                                colors={productInfo[2].data}
                                                product={getValues()}
                                                // // setSizeColor={setSizeColor}
                                                // index={index}
                                                register={register}
                                                errors={errors}
                                            />
                                        // <div key={index}>ss</div>
                                        )
                                }
                                {/*<h3 className="fileuploder">*/}
                                {/*    <label htmlFor="ex_file">대표사진 등록 : </label>*/}
                                {/*    <input*/}
                                {/*        type="file"*/}
                                {/*        id="ex_file"*/}
                                {/*        name="filename"*/}
                                {/*        multiple*/}
                                {/*        onChange={handleFilesChange}*/}
                                {/*    />*/}
                                {/*</h3>*/}
                                <div>
                                    <NoSsrEditor
                                        content={initProductData.description}
                                        onChangeDescription={onChangeDescription}
                                        editorRef={refs}
                                        id="description"
                                        register={register}
                                        erros
                                    />
                                </div>
                                <Button
                                    type="submit"
                                    className="btn-submit"
                                    variant="contained"
                                    size="large"
                                    endIcon={<SendIcon/>}>
                                    {buttonTitle}
                                </Button>
                            </form>
                        </div>
                    }

                </StyledSetion>
            </StyledContent>
        </StyledContainer>
    );
}

export default UploadProductComponents;