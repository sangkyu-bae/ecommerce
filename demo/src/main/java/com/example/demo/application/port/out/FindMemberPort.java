package com.example.demo.application.port.out;

import com.example.demo.module.domain.member.MemberVo;
import com.example.demo.module.domain.member.entity.Member;

import java.util.List;

public interface FindMemberPort {
    boolean existMember(long userId);

    List<Member> findMemberListByAddress(MemberVo.MemberAddress memberAddress);

    Member findMemberByMemberId(MemberVo.MemberUserId memberUserId);

    List<Member> findMemberAll();
}
