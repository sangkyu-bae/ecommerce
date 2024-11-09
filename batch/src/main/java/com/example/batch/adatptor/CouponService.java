package com.example.batch.adatptor;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class CouponService {

    private final JdbcTemplate jdbcTemplate;

    private final StringRedisTemplate redisTemplate;

    @Transactional
    public void bulkInsertCouponComponent(List<Map<String, Object>> reqList) throws Exception {
        log.info("????>>>>>>>>>");
        jdbcTemplate.batchUpdate(
                "INSERT INTO tb_coupon_component (user_id, status, coupon_id) VALUES (?, ?, ?)",
                new BatchPreparedStatementSetter() {

                    @Override
                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        Map<String,Object> req = reqList.get(i);
                        ps.setLong(1, (Long) req.get("userId"));
                        ps.setInt(2, (Integer) req.get("status")); // Assuming status is an enum, convert to string
//                        ps.setTimestamp(3, Timestamp.valueOf(couponComponent.getEndAt())); // Convert LocalDateTime to Timestamp
                        ps.setLong(3, (Integer) req.get("couponId")); // Assuming fetchCoupon has an ID field
                    }

                    @Override
                    public int getBatchSize() {
                        log.info(String.valueOf(reqList.size()));
                        return reqList.size();
                    }
                }
        );

//        throw new RuntimeException("db insert failed");
    }

    @Transactional
    public void removeEventCouponByRedis(String key){
        ZSetOperations<String, String> zSetOperations = redisTemplate.opsForZSet();

        zSetOperations.removeRange(key,0,400);

        throw new RuntimeException("remove fail");
    }

}
