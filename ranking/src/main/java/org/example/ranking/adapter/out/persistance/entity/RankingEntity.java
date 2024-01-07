package org.example.ranking.adapter.out.persistance.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

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

    private long clickNum;

    private long saleNum;

}
