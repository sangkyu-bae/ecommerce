package com.example.demo.application.service;

import com.example.demo.adapter.axon.command.CreateRegisterMemberCommand;
import com.example.demo.adapter.out.persistence.MemberMapper;
import com.example.demo.application.port.in.command.RegisterMemberCommand;
import com.example.demo.application.port.in.usecase.RegisterMemberUseCase;
import com.example.demo.application.port.out.RegisterMemberPort;
import com.example.demo.module.domain.member.MemberVo;
import com.example.demo.module.domain.member.entity.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.example.UseCase;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;


@UseCase
@Transactional
@Slf4j
@RequiredArgsConstructor
public class RegisterMemberService implements RegisterMemberUseCase {

    private final RegisterMemberPort registerMemberPort;

    private final CommandGateway commandGateway;

    private final MemberMapper memberMapper;

    private final PasswordEncoder passwordEncoder;
    @Override
    public MemberVo registerMember(RegisterMemberCommand command) {
        String password = passwordEncoder.encode(command.getPassword());

        CreateRegisterMemberCommand axonCommand = CreateRegisterMemberCommand.builder()
                .email(command.getEmail())
                .name(command.getName())
                .password(password)
                .build();

        try {
            Object result = commandGateway.sendAndWait(axonCommand);

            MemberVo memberVo = MemberVo.createGenerateMemberVo(
                    new MemberVo.MemberUserId(null),
                    new MemberVo.MemberEmail(command.getEmail()),
                    new MemberVo.MemberPassword(password),
                    new MemberVo.MemberName(command.getName()),
                    new MemberVo.MemberAddress(null),
                    new MemberVo.MemberEmailVerified(false),
                    new MemberVo.MemberEmailCheckToken(null),
                    new MemberVo.MemberPhone(null),
                    new MemberVo.MemberNotificationByEmail(false),
                    new MemberVo.MemberJoinAt(LocalDateTime.now()),
                    new MemberVo.MemberUpdateAt(LocalDateTime.now()),
                    new MemberVo.MemberAggregateIdentifier(result.toString())
            );

            Member member = registerMemberPort.registerMember(memberVo);

            return memberMapper.mapToDomainEntity(member);
        } catch (Exception e) {
            log.error("Error occurred while registering member: " + e.getMessage());
            throw new RuntimeException("Error occurred while registering member", e);
        }
    }
}
