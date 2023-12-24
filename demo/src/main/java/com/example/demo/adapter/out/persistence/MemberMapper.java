package com.example.demo.adapter.out.persistence;

import com.example.demo.module.domain.member.MemberVo;
import com.example.demo.module.domain.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberMapper {
    private final ModelMapper modelMapper;

    public MemberVo mapToDomainEntity(Member member) {
        return MemberVo.createGenerateMemberVo(
                new MemberVo.MemberUserId(member.getUserId()),
                new MemberVo.MemberEmail(member.getEmail()),
                new MemberVo.MemberPassword(member.getPassword()),
                new MemberVo.MemberName(member.getName()),
                new MemberVo.MemberAddress(member.getAddress()),
                new MemberVo.MemberEmailVerified(member.isEmailVerified()),
                new MemberVo.MemberEmailCheckToken(member.getEmailCheckToken()),
                new MemberVo.MemberPhone(member.getPhone()),
                new MemberVo.MemberNotificationByEmail(member.isNotificationByEmail()),
                new MemberVo.MemberJoinAt(member.getJoinAt()),
                new MemberVo.MemberUpdateAt(member.getUpdateAt()),
                new MemberVo.MemberAggregateIdentifier(member.getAggregateIdentifier())
        );
    }
}
