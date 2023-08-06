export const getTotalPageNumber =(totalCount:number,pageSize:number)=>{
    let totalPageNumber:number = Math.ceil(totalCount/pageSize);
    return totalPageNumber;
}