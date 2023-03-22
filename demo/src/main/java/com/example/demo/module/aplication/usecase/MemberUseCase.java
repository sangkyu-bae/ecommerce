package com.example.demo.module.aplication.usecase;

import com.example.demo.module.domain.member.dto.MemberDto;
import com.example.demo.module.domain.member.entity.Member;
import com.example.demo.module.domain.member.service.MemberReadService;
import com.example.demo.module.domain.member.service.MemberWriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberUseCase {

    private final MemberWriteService memberWriteService;
    private final MemberReadService memberReadService;
    public MemberDto joinMemberExecute(Member joinMember){
        Member saveMember=memberWriteService.createMember(joinMember);
        MemberDto memberDto=memberReadService.toMemberDto(saveMember);

        return memberDto;
    }
}
