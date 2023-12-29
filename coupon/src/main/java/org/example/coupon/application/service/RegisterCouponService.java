package org.example.coupon.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.example.UseCase;
import org.example.coupon.adapter.axon.command.CouponRequestCreateCommand;
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

    private final CommandGateway commandGateway;

    @Override
    public CouponVo RegisterCouponByAllUser(RegisterCouponCommand command) {

        List<Member> memberList = getMemberPort.getMemberList();

        CouponVo couponVo = CouponVo.createGenerateCouponVo(
                new CouponVo.CouponId(null),
                new CouponVo.CouponCreateAdminId(command.getCreateAdminUserId()),
                new CouponVo.CouponSalePercent(command.getSalePercent()),
                new CouponVo.CouponName(command.getName()),
                new CouponVo.CouponCreateAt(LocalDateTime.now()),
                null,
                new CouponVo.CouponAggregateIdentifier(null)
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

    @Override
    public CouponVo RegisterCouponByAllUserWithAxon(RegisterCouponCommand command) {
        List<Member> memberList = getMemberPort.getMemberList();

        LocalDateTime endAt = LocalDateTime.now().plusDays(5);
        List<CouponRequestCreateCommand.CouponComponentRequestCreateCommand> couponComponentRequestCreateCommands = memberList.stream()
                .map(member -> new CouponRequestCreateCommand.CouponComponentRequestCreateCommand(
                        member.getUserId(),
                        CouponComponentVo.CouponStatusCode.PUBLISH.getStatus(),
                        endAt
                )).collect(Collectors.toList());


        CouponRequestCreateCommand axonCommand = CouponRequestCreateCommand.builder()
                .createAdminId(command.getCreateAdminUserId())
                .createAt(LocalDateTime.now())
                .salePercent(command.getSalePercent())
                .name(command.getName())
                .couponComponentRequestCreateCommands(couponComponentRequestCreateCommands)
                .build();

        try {
            Object result = commandGateway.sendAndWait(axonCommand);
            CouponVo couponVo = CouponVo.createGenerateCouponVo(
                    new CouponVo.CouponId(null),
                    new CouponVo.CouponCreateAdminId(command.getCreateAdminUserId()),
                    new CouponVo.CouponSalePercent(command.getSalePercent()),
                    new CouponVo.CouponName(command.getName()),
                    new CouponVo.CouponCreateAt(LocalDateTime.now()),
                    null,
                    new CouponVo.CouponAggregateIdentifier(result.toString())
            );

            List<CouponComponentVo> couponComponentVos = memberList.stream()
                    .map(member -> CouponComponentVo.createGenerateCouponComponentVo(
                            new CouponComponentVo.CouponComponentId(null),
                            new CouponComponentVo.CouponComponentUserId(member.getUserId()),
                            CouponComponentVo.CouponStatusCode.PUBLISH,
                            new CouponComponentVo.CouponComponentEndAt(endAt),
                            couponVo
                    )).collect(Collectors.toList());
            couponVo.setCouponComponentVoList(couponComponentVos);

            CouponEntity coupon = registerCouponPort.registerCouponByAllUser(couponVo);

            return couponMapper.mapToDomainEntity(coupon);
        } catch (Exception e) {

        }

        return null;
    }
}
