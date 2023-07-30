export const getTotalPageNumber =(totalCount:number,pageSize:number)=>{
    let totalPageNumber:number = Math.ceil(totalCount/pageSize);
    console.log(totalPageNumber)
    return totalPageNumber;
}