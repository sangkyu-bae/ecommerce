const Validation = {
    rules: {
        required: "이름을 입력해주세요.",
        maxLength: {
            value: 10,
            message: "이름은 최대 10자까지 입력 가능합니다.",
        },
        minLength: {
            value: 2,
            message: "이름은 최소 2자 이상 입력해야 합니다.",
        },
    },
    email: {
        required: true,
        pattern: {
            value:
                /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i,
            message: "이메일 형식에 맞지 않습니다.",
        },
    },
    password: {
        required: true,
        minLength: 8,
        maxLength: 20,
        pattern: {
            value: /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,20}$/,
            message: '비밀번호는 영문자, 특수문자, 숫자를 모두 포함하여 8자 이상 입력하세요.',
        },
    },
    name: {
        required: true,
        maxLength: {
            value: 20,
            message: "상품명은 최대 20자까지 입력 가능합니다.",
        },
        minLength: {
            value: 2,
            message: "상품명은 최소 2자 이상 입력해야 합니다.",
        },
    },
    price: {
        required: true,
        minLength: {
            value: 3,
            message: '상품 가격은 100원 이상입니다.'
        }
    },
    color :{
        required:true,
        message: "컬러 값을 등록하세요"
    },
    colorValueValidate : function (productComponents : ProductComponent[]){
        for(var i in productComponents){
            const component = productComponents[i];
            if(component.sizes.length == 0){
                return false;
            }
        }

        return true;
    },
    colorValidate: function (colorData: ColorData[]) {
        console.log(colorData)
        if(colorData.length == 0){
            return false;
        }
        const errorColor = colorData.find(colorObj => colorObj.colorSize.length == 0);
        if (errorColor) {
            return false;
        }
        return true;
    }
}


export default Validation;