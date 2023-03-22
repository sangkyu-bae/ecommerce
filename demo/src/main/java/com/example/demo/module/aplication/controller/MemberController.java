package com.example.demo.module.aplication.controller;

import com.example.demo.module.aplication.usecase.MemberUseCase;
import com.example.demo.module.domain.member.dto.MemberDto;
import com.example.demo.module.domain.member.entity.Member;
import com.example.demo.module.domain.member.service.MemberWriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberUseCase memberUseCase;

    @PostMapping("/join")
    public ResponseEntity<MemberDto> joinMember(Member member){
        MemberDto memberDto=memberUseCase.joinMemberExecute(member);
        if(memberDto==null){
            throw new IllegalArgumentException("회원가입이 실패하였습니다. 다시 시도 하시오.");
        }
        return ResponseEntity.ok().body(memberDto);
    }
}
