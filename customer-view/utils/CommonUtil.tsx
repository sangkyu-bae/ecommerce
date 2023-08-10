
export const getTotalPageNumber =(totalCount:number,pageSize:number) :number=>{
    let totalPageNumber:number = Math.ceil(totalCount/pageSize);
    return totalPageNumber;
}

class Util {
    constructor() {
    }
     getTotalPageNumber =(totalCount:number,pageSize:number) :number=>{
        let totalPageNumber:number = Math.ceil(totalCount/pageSize);
        return totalPageNumber;
    }
    addCommasToNumber=(price:number) :string=> {
        return price.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    }
}

export default Util;