import ApiCommon from "@/shared/api/common/ApiCommon";
import {rankRequest} from "@/shared/constants/Url";

export const RankApi ={
    readClickRank : async ()=>{
        console.log(rankRequest.readProductRank)
        const {data} = await ApiCommon.basicAPI.get(rankRequest.readProductRank);
        return data;
    }
}