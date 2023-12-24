package com.example.demo.application.port.out;

import com.example.demo.module.domain.member.MemberVo;
import com.example.demo.module.domain.member.entity.Member;

public interface RegisterMemberPort {

    Member registerMember(MemberVo member);
}
