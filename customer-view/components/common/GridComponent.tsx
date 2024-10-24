import React from 'react';
import styled from "styled-components";

interface InfoProps {
    title: string;
    children: React.ReactNode;
}

function GridComponent({title, children}: InfoProps) {
    return (
        <GridContainer>
            <h4>{title}</h4>
            <h5>{children}</h5>
        </GridContainer>
    );
}

const GridContainer = styled.div`
  padding: 10px 0px;
  text-align: center;
  h4 {
    padding: 5px 0px;
    font-size: 2em;
    font-weight: 600;
  }
  h5 {
    font-size: 1rem;
    font-weight: 400;
  }
  img {
    width: 100%;
    height: 100%;
    padding: 3px 0 3px 16px;
  }
`;
export const StyledContent = styled.div`
        flex : ${(props) => props.isFull ? '1' : '0.87'};
        background-color : #FFFAF0;
        display:flex;
    `
export const StyledSetion = styled.div`
    flex: ${(props) => props.isFull ? '1' : '0.8'};
    .main-section{
        width : 80%;
        margin : 0 auto;
    }
    .btn-submit{
        float: right;
        margin-top: 2em;
    }
    .first-section{
        flex:1;
        width : 100%;
        margin : 0 auto;
    }
    .flex{
        display:flex;
        flex-wrap: wrap;
    }
    .flex .image{
        flex:0.5;
    }
    .flex .section{
        flex:0.5;
    }
    .flex .bold{
        font-weight: bold;
        font-size : 1.2em;
    }
    .bold .gray{
        color : gray;
    }
`

export const StyledOrderBox = styled.div`
    .main-box{
        text-align: center;
        height: 3em;
        display:flex;
        border :1px solid gray;
    }
    .flex-box{
        display:flex;
    }
    .img-box {
        flex:0.2;
    }
    .second-flex{
        flex:0.7;
    }
    .title-box{
        padding-top:1%;
        height:25%;
    }
    .sub-box{
        text-align: center;
        height: 8em;
        display:flex;
        border-bottom :1px solid gray;
    }
    .main-box-first .center{
        margin-top:1%;
        font-weight: bold;
    }
    .center{
        margin-top:3%;
    }
    .main-box-element-right{
        border-right:1px solid gray;
    }
    #order{
        flex:0.5;
        display:flex;
    }
    .main-box-first{
        flex:0.7;
        display:flex;
    }
   
    .main-box-remain{
        flex:0.2;
    }
    .sub-container-box{
        display:flex
        border-bottom : 1px solid gray;
        height:5em;
    }
    .sub-info-option-box{
        background-color:#ddd;
        color:black;
        height:65%;
        text-align: left;
        padding:2%;
    }
    .quantity-box{
        font-weight: bold;
    }
    
    .text-left{
        text-align:left;
    }
    
    .full-div{
        width:100%;
    }

    
    .herf-div{
        width:80%;
        margin:0 auto;
    }
    
`
export const StyledMenu = styled.div`
    flex:0.2;
`
export const StyledContainer = styled.div`
        display : flex;
        width : 100%;
        height : 100vh;
        background-color : #FFFAF0;
  `;

export const Container = styled.div`
        display : flex;
        width : 100%;
        background-color : #FFFAF0;
  
  `;

export const BoxContainer = styled.div`
    width : 90%;
    margin: 0 auto;
    .full{
        width :100%;   
    }
    .block {
        display:block;
    }
    .normal-th{
        width: 20%;
    }
    .normal-td{
       text-align: center;  
       font-size : 16px;
       font-weight: 550;
    }
    
    .normal-td img {
        width : 15%;
        height : 10%;
    }
    
    .t-head{
        background-color: #dee2e6;
        height: 3em;
    }
    
    .flex {
        display:flex
    }
    .border-b {
        border-bottom : 1px solid gray;
        margin-bottom : 2em;
    }
    
    .position-r {
        position: relative;
    }
    .position-a{
        position: absolute;
    }
    
    .normal-b{
        width : 40%;
    }
    
    th{
         font-size: 1.2em;
    }
    
    .bold {
        font-weight: bold;
        font-size : 1.1em;
    }
`
export default GridComponent;