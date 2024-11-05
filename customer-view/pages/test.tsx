import React, {useEffect} from 'react';
import PropTypes from 'prop-types';
import ApiCommon from "@/shared/api/common/ApiCommon";

function Test(props) {

    useEffect( ()=>{
        const tt = () => {
            return ApiCommon.basicAPI.get('http://localhost:8080/member/v1/test')
                .then(response => response.data)  // 데이터를 반환하는 Promise
                .catch(error => {
                    console.error('Error fetching data:', error);
                    throw error;  // 에러를 다시 던져서 호출 측에서 처리할 수 있게
                });
        };

        // Promise 사용
        tt().then(data => {
           console.log(data);
           console.log(parse(data))
        }).catch(error => {
            console.error('Error:', error);  // 에러 처리
        });
    },[])

    const parse = (data) =>{
        var obj;
        if(typeof  data =='string'){
            try{
                obj =JSON.parse(data)
            }catch (e){
                console.log(e.name +'\n' + e.message);
            }
        }else{
            obj = data;
        }

        return obj;
    }



    return (
        <div></div>
    );
}

export default Test;