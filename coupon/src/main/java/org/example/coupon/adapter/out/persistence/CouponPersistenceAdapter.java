package org.example.coupon.adapter.out.persistence;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.PersistenceAdapter;
import org.example.coupon.adapter.out.persistence.entity.CouponComponentEntity;
import org.example.coupon.adapter.out.persistence.entity.CouponEntity;
import org.example.coupon.adapter.out.persistence.repository.CouponComponentEntityRepository;
import org.example.coupon.adapter.out.persistence.repository.CouponEntityRepository;
import org.example.coupon.application.port.out.FindCouponPort;
import org.example.coupon.application.port.out.RegisterCouponPort;
import org.example.coupon.application.port.out.UpdateCouponPort;
import org.example.coupon.domain.Coupon;
import org.example.coupon.domain.CouponComponent;
import org.example.coupon.domain.Event;
import org.example.coupon.infra.error.CouponErrorCode;
import org.example.coupon.infra.error.ErrorException;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@PersistenceAdapter
@RequiredArgsConstructor
@Slf4j
public class CouponPersistenceAdapter implements RegisterCouponPort, FindCouponPort, UpdateCouponPort {
    private final CouponComponentEntityRepository couponComponentEntityRepository;

    private final CouponEntityRepository couponEntityRepository;

    private final JdbcTemplate jdbcTemplate;
    @Override
    public CouponEntity registerCouponByAllUser(Coupon couponVo) {
        CouponEntity coupon = CouponEntity.builder()
                .name(couponVo.getName())
                .createAdminId(couponVo.getCreateAdminId())
                .salePercent(couponVo.getSalePercent())
                .createAt(couponVo.getCreatAt())
                .aggregateIdentifier(couponVo.getAggregateIdentifier())
                .build();

        List<CouponComponentEntity> couponComponentEntityList = couponVo.getCouponComponentVoList().stream()
                .map(couponComponentVo -> CouponComponentEntity.builder()
                        .userId(couponComponentVo.getUserId())
                        .endAt(couponComponentVo.getEndAt())
                        .coupon(coupon)
                        .status(couponComponentVo.getStatus())
                        .build())
                .collect(Collectors.toList());

        coupon.setCouponComponents(couponComponentEntityList);

        return couponEntityRepository.save(coupon);
    }

    @Override
    @Transactional
    public CouponComponentEntity issuanceCoupon(CouponComponent couponComponent,  Coupon.CouponId couponId) {

        CouponEntity coupon = findCouponByCouponId(couponId);

        CouponComponentEntity couponComponentEntity = CouponComponentEntity.builder()
                .coupon(coupon)
                .userId(couponComponent.getUserId())
                .status(couponComponent.getStatus())
                .endAt(couponComponent.getEndAt())
                .build();

        CouponComponentEntity registerComponent = couponComponentEntityRepository.save(couponComponentEntity);

        coupon.addCouponComponent(registerComponent);

        return registerComponent;
    }

    /**
     * todo : jdbc bulk insert로 성능 개선
     * */
    @Override
    public List<CouponEntity> bulkRegisterCoupon(List<Coupon> couponList) {
        List<CouponEntity> couponEntityList = new ArrayList<>();

        for(Coupon coupon : couponList){
            CouponEntity couponEntity = CouponEntity.builder()
                    .salePercent(coupon.getSalePercent())
                    .name(coupon.getName())
                    .createAdminId(coupon.getCreateAdminId())
                    .createAt(coupon.getCreatAt())
                    .build();
            List<CouponComponentEntity> couponComponentEntityList = new ArrayList<>();
            for(CouponComponent couponComponent : coupon.getCouponComponentVoList()){
                CouponComponentEntity couponComponentEntity = CouponComponentEntity.builder()
                        .coupon(couponEntity)
                        .status(couponComponent.getStatus())
                        .endAt(couponEntity.getCreateAt())
                        .build();
                couponComponentEntityList.add(couponComponentEntity);
            }

            couponEntity.setCouponComponents(couponComponentEntityList);

            couponEntityList.add(couponEntity);
        }

        return couponEntityRepository.saveAll(couponEntityList);

    }

    @Override
    public CouponEntity registerCoupon(Coupon coupon) {
        CouponEntity couponEntity = CouponEntity.builder()
                .salePercent(coupon.getSalePercent())
                .createAt(coupon.getCreatAt())
                .createAdminId(coupon.getCreateAdminId())
                .name(coupon.getName())
                .build();

        return couponEntityRepository.save(couponEntity);
    }


    @Override
    public CouponEntity findCouponByCouponId(Coupon.CouponId couponId) {
        return couponEntityRepository.findById(couponId.getId())
                .orElseThrow(()->new ErrorException(CouponErrorCode.COUPON_NOT_FOUND,"findCouponByCouponComponentId"));
    }

    @Override
    public CouponEntity findByCouponName(Coupon.CouponName couponName) {
        List<CouponEntity> couponEntityList = couponEntityRepository.findByName(couponName.getName());

        if(couponEntityList ==null ||couponEntityList.isEmpty()){
            throw new ErrorException(CouponErrorCode.COUPON_NOT_FOUND,"findByCouponName");
        }

        return couponEntityList.get(0);
    }

    @Override
    public CouponEntity updateCoupon(long couponId, long userId) {
        CouponEntity couponEntity = couponEntityRepository.findById(couponId)
                .orElseThrow(()->new ErrorException(CouponErrorCode.COUPON_NOT_FOUND,"findCouponByCouponComponentId"));

        couponEntity.checkAndUpdateCoupon(userId);

        return couponEntityRepository.save(couponEntity);
    }

    @Override
    public CouponEntity updateCouponComponent(List<CouponComponent> couponComponentList, CouponEntity fetchCoupon) {

        for(CouponComponent couponComponent: couponComponentList){
            CouponComponentEntity couponComponentEntity = CouponComponentEntity.builder()
                    .endAt(couponComponent.getEndAt())
                    .coupon(fetchCoupon)
                    .status(couponComponent.getStatus())
                    .userId(couponComponent.getUserId())
                    .build();

            fetchCoupon.addCouponComponent(couponComponentEntity);
        }

        return couponEntityRepository.save(fetchCoupon);
    }

    @Override
    public void bulkInsertCouponComponent(List<CouponComponent> couponComponentList, CouponEntity fetchCoupon){
        jdbcTemplate.batchUpdate(
                    "INSERT INTO tb_coupon_component (user_id, status, end_at,coupon_id) VALUES (?, ?, ?,?)",
                    new BatchPreparedStatementSetter() {

                        @Override
                        public void setValues(PreparedStatement ps, int i) throws SQLException {
                            CouponComponent couponComponent = couponComponentList.get(i);
                            ps.setLong(1, couponComponent.getUserId());
                            ps.setInt(2,couponComponent.getStatus()); // Assuming status is an enum, convert to string
                            ps.setTimestamp(3, Timestamp.valueOf(couponComponent.getEndAt())); // Convert LocalDateTime to Timestamp
                            ps.setLong(4, fetchCoupon.getId()); // Assuming fetchCoupon has an ID field
                        }

                        @Override
                        public int getBatchSize() {
                            return couponComponentList.size();
                        }
                    }
                );
    }
}
