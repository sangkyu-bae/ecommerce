import ApiCommon from "@/api/common/ApiCommon";
import {rankRequest} from "@/constants/Url";

export const RankApi ={
    readClickRank : async ()=>{
        const {data} = await ApiCommon.basicAPI.get(rankRequest.readProductRank);
        return data;
    }
}