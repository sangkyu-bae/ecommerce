package com.example.demo.application.service;

import com.example.demo.adapter.out.persistence.MemberMapper;
import com.example.demo.application.port.in.command.FindMemberByAddressCommand;
import com.example.demo.application.port.in.command.FindMemberCommand;
import com.example.demo.application.port.in.usecase.FindMemberUseCase;
import com.example.demo.application.port.out.FindMemberPort;
import com.example.demo.module.domain.member.MemberVo;
import com.example.demo.module.domain.member.entity.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.UseCase;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@UseCase
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FindMemberService implements FindMemberUseCase {

    private final FindMemberPort findMemberPort;
    private final MemberMapper memberMapper;
    @Override
    public boolean existMember(FindMemberCommand command) {
        return findMemberPort.existMember(command.getUserId());
    }

    @Override
    public List<MemberVo> findMemberListByAddress(FindMemberByAddressCommand command) {
        List<Member> members = findMemberPort.findMemberListByAddress(new MemberVo.MemberAddress(command.getAddressName()));
        List<MemberVo> memberVoList = members.stream().
                map(member-> memberMapper.mapToDomainEntity(member))
                .collect(Collectors.toList());

        return memberVoList;
    }
}
