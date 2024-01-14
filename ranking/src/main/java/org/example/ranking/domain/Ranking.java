package org.example.ranking.domain;

import lombok.*;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class Ranking {

    private Long id;

    private long productId;

    private long clickNum;

    private long saleNum;

    public static Ranking createGenerateRanking(
            RankingId rankingId,
            RankingProductId rankingProductId,
            RankingClickNum rankingClickNum,
            RankingSaleNum rankingSaleNum

    ){
        return new Ranking(
                rankingId.getId(),
                rankingProductId.getProductId(),
                rankingClickNum.getClickNum(),
                rankingSaleNum.getSaleNum()
        );
    }

    @Value
    public static class RankingId{
        public RankingId(Long value){
            this.id = value;
        }
        private Long id;
    }

    @Value
    public static class RankingProductId {
        public RankingProductId(long value){
            this.productId= value;
        }

        private long productId;
    }

    @Value
    public static class RankingClickNum{
        public RankingClickNum(long value){
            this.clickNum = value;
        }

        private long clickNum;
    }

    @Value
    public static class RankingSaleNum{
        public RankingSaleNum(long value){
            this.saleNum = value;
        }

        private long saleNum;
    }

}
