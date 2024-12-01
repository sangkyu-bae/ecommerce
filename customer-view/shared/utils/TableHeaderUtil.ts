interface TableHeader {
    title: string;
    class: string[];
}

type TableMap = Record<string, TableHeader[]>;

const tableMap :TableMap  = {
    order : [
        {
            title :'상품정보',
            class:['main-box-first','main-box-element-right']
        },
        {
            title:'수량',
            class: ['main-box-element-right','main-box-remain']
        },
        {
            title:'주문금액',
            class:['main-box-element-right','main-box-remain']
        },
        {
            title:'주문현황',
            class: ['main-box-remain']
        }
    ]
}

class TableHeaderUtil{
    type: string;
    constructor(type : string) {
        this.type = type;
    }

    getHeaderTable(): TableHeader[]{
        const headers = tableMap[this.type];
        if (!headers) {
            //에러처리 필요
            return [];
        }
        return headers;
    }


}

export default TableHeaderUtil;