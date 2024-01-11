package org.example.ranking.adapter.out.persistance.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of="id")
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_ranking") @Builder
public class RankingEntity {

    @Id @GeneratedValue
    private Long id;

    private long productId;

    private String productName;

    private long clickNum;

    private long saleNum;

    private LocalDateTime createAt;

    private LocalDateTime updateAt;

    private void incrementClickNum(int clickCnt){
        clickNum += clickCnt;
    }

    private void incrementSaleNum(int saleCnt){
        saleNum += saleNum;
    }

    public void changeBatchData(int clickCnt, int saleCnt){
        incrementClickNum(clickCnt);
        incrementSaleNum(saleCnt);
        setUpdateAt(LocalDateTime.now());
    }
}
