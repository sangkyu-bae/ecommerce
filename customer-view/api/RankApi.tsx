import ApiCommon from "@/api/common/ApiCommon";
import {rankRequest} from "@/constants/Url";

export const RankApi ={
    readClickRank : async ()=>{
        console.log(rankRequest.readProductRank)
        const {data} = await ApiCommon.basicAPI.get(rankRequest.readProductRank);
        return data;
    }
}