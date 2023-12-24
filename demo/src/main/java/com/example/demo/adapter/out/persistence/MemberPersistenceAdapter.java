package com.example.demo.adapter.out.persistence;

import com.example.demo.application.port.out.FindMemberPort;
import com.example.demo.application.port.out.RegisterMemberPort;
import com.example.demo.module.domain.member.MemberVo;
import com.example.demo.module.domain.member.entity.Member;
import com.example.demo.module.domain.member.respository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.PersistenceAdapter;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@PersistenceAdapter
@RequiredArgsConstructor
@Slf4j
public class MemberPersistenceAdapter implements FindMemberPort, RegisterMemberPort {

    private final MemberRepository memberRepository;

    @Override
    public boolean existMember(long userId) {
        return memberRepository.existsById(userId);
    }

    @Override
    public List<Member> findMemberListByAddress(MemberVo.MemberAddress memberAddress) {
        return memberRepository.findByAddress(memberAddress.getAddress());
    }

    @Override
    public Member findMemberByMemberId(MemberVo.MemberUserId memberUserId) {
        return memberRepository.findById(memberUserId.getUserId()).orElseThrow();
    }

    @Override
    public Member registerMember(MemberVo member) {
        Member joinMember = Member.builder()
                .email(member.getEmail())
                .name(member.getName())
                .password(member.getPassword())
                .aggregateIdentifier(member.getAggregateIdentifier())
                .build();
        return memberRepository.save(joinMember);
    }
}
