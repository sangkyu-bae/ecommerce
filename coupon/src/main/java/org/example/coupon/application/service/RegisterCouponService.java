package org.example.coupon.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.UseCase;
import org.example.coupon.adapter.out.persistence.CouponMapper;
import org.example.coupon.adapter.out.persistence.entity.CouponEntity;
import org.example.coupon.adapter.out.service.Member;
import org.example.coupon.application.port.in.command.RegisterCouponCommand;
import org.example.coupon.application.port.in.usecase.RegisterCouponUseCase;
import org.example.coupon.application.port.out.GetMemberPort;
import org.example.coupon.application.port.out.RegisterCouponPort;
import org.example.coupon.domain.CouponComponentVo;
import org.example.coupon.domain.CouponVo;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@UseCase
@Slf4j
@Transactional
public class RegisterCouponService implements RegisterCouponUseCase {

    private final GetMemberPort getMemberPort;

    private final RegisterCouponPort registerCouponPort;

    private final CouponMapper couponMapper;

    @Override
    public CouponVo RegisterCouponByAllUser(RegisterCouponCommand command) {

        List<Member> memberList = getMemberPort.getMemberList();

        CouponVo couponVo = CouponVo.createGenerateCouponVo(
                new CouponVo.CouponId(null),
                new CouponVo.CouponCreateAdminId(command.getCreateAdminUserId()),
                new CouponVo.CouponSalePercent(command.getSalePercent()),
                new CouponVo.CouponName(command.getName()),
                new CouponVo.CouponCreateAt(LocalDateTime.now()),
                null
        );

        List<CouponComponentVo> couponComponentVos = memberList.stream()
                .map(member -> CouponComponentVo.createGenerateCouponComponentVo(
                        new CouponComponentVo.CouponComponentId(null),
                        new CouponComponentVo.CouponComponentUserId(member.getUserId()),
                        CouponComponentVo.CouponStatusCode.PUBLISH,
                        new CouponComponentVo.CouponComponentEndAt(LocalDateTime.now().plusDays(5)),
                        couponVo
                )).collect(Collectors.toList());

        couponVo.setCouponComponentVoList(couponComponentVos);

        CouponEntity coupon = registerCouponPort.registerCouponByAllUser(couponVo);

        return couponMapper.mapToDomainEntity(coupon);
    }
}
