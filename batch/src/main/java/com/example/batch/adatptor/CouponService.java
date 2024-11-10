package com.example.batch.adatptor;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class CouponService {

    private final JdbcTemplate jdbcTemplate;

    private final StringRedisTemplate redisTemplate;

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Transactional
    public void bulkInsertCouponComponent(List<Map<String, Object>> reqList) throws Exception {
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
    public void removeEventCouponByRedis(String key,long end){
        ZSetOperations<String, String> zSetOperations = redisTemplate.opsForZSet();

        log.info("end key : {}",end );
        zSetOperations.removeRange(key,0,end -1);

//        throw new RuntimeException("remove fail");
    }

    @Transactional(readOnly = true)
    public List<Map<String,Object>> findEventCouponList(){
        StringBuilder sqlSb = new StringBuilder();

        sqlSb.append("select id,coupon_name as couponName ,quantity,sale_percent as salePercent ");
        sqlSb.append("from tb_event ");
        sqlSb.append("where start_at < now() ");
        sqlSb.append("and end_at > now();");

        return jdbcTemplate.query(sqlSb.toString(), new RowMapper <Map<String,Object>>() {
            @Override
            public Map<String,Object> mapRow(ResultSet rs, int rowNum) throws SQLException {
                Map<String,Object> resMap = new HashMap<>();

                resMap.put("eventId", rs.getLong("id"));
                resMap.put("quantity", rs.getInt("quantity"));

                return resMap;
            }
        });
    }

    @Transactional
    public void updateEventCouponQuantity(long eventId, long quantity){

        StringBuilder sqlSb = new StringBuilder();

        sqlSb.append("update tb_event ");
        sqlSb.append("set quantity = :quantity ");
        sqlSb.append("where id = :id ");

        String sql = sqlSb.toString();

        Map<String, Object> params = new HashMap<>();
        params.put("quantity", quantity);
        params.put("id", eventId);

        namedParameterJdbcTemplate.update(sql, params);
    }

}
