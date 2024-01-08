package org.example.ranking.application.port.out;

import org.example.ranking.adapter.out.persistance.RedisRanking;

import java.util.List;

public interface FindRankingRedisPort {

    List<RedisRanking> findRankingAll();
}
