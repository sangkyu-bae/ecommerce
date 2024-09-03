export const orderValidation = {
    phone :{
        required: '전화번호는 필수 항목입니다',
        pattern: {
            value: /^[0-9\b]+$/,
            message: '유효한 전화번호를 입력해주세요',
        }
    },
    address : {
        required: '배송지는 필수 항목입니다.',
        minLength :{
            value:2,
            message: '배송지는 최소 2자 이상 입력해야 합니다.'
        },
        maxLength: {
            value : 20,
            message : '배송지는 최대 20자 까지 입력 가능합니다.'
        }
    }
}