package com.example.demo.module.aplication.controller;

import com.example.demo.module.aplication.usecase.MemberUseCase;
import com.example.demo.module.domain.member.dto.JoinMemberDto;
import com.example.demo.module.domain.member.dto.MemberDto;
import com.example.demo.module.domain.member.entity.Member;
import com.example.demo.module.domain.member.service.MemberWriteService;
import com.example.demo.module.domain.member.validatior.JoinMemberDtoValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.ValidationException;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberUseCase memberUseCase;

    private final JoinMemberDtoValidator joinMemberDtoValidator;
    @InitBinder("joinMemberDto")
    public void initBinder(WebDataBinder webDataBinder){
        webDataBinder.addValidators(joinMemberDtoValidator);
    }
    @PostMapping("/join")
    public ResponseEntity<MemberDto> joinMember(@RequestBody @Valid JoinMemberDto joinMemberDto, Errors errors){
        if(errors.hasErrors()){
            throw new ValidationException("회원가입 형식이 잘못되었습니다.");
        }
        
        MemberDto memberDto=memberUseCase.joinMemberExecute(joinMemberDto);
        if(memberDto==null){
            throw new IllegalArgumentException("회원가입이 실패하였습니다. 다시 시도 하시오.");
        }
        return ResponseEntity.ok().body(memberDto);
    }
}
