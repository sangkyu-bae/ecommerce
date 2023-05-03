package com.example.demo.module.aplication.controller;

import com.example.demo.module.aplication.usecase.MemberUseCase;
import com.example.demo.module.domain.member.dto.JoinMemberDto;
import com.example.demo.module.domain.member.dto.MemberDto;
import com.example.demo.module.domain.member.entity.Member;
import com.example.demo.module.domain.member.service.MemberWriteService;
import com.example.demo.module.domain.member.validatior.JoinMemberDtoValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.ValidationException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
public class MemberController {
    private final MemberUseCase memberUseCase;
    private final JoinMemberDtoValidator joinMemberDtoValidator;
    @InitBinder("joinMemberDto")
    public void initBinder(WebDataBinder webDataBinder){
        webDataBinder.addValidators(joinMemberDtoValidator);
    }
    @PostMapping("/sign-up")
    public ResponseEntity<MemberDto> joinMember(@RequestBody @Valid JoinMemberDto joinMemberDto, Errors errors){
        if(errors.hasErrors()){
//            return ResponseEntity.badRequest().body("회원가입 형식이 잘못됨");
            throw new ValidationException("회원가입 형식이 잘못되었습니다.");
        }
        MemberDto memberDto=memberUseCase.joinMemberExecute(joinMemberDto);;
        if (memberDto == null) {
            throw new IllegalArgumentException("회원가입이 실패하였습니다. 다시 시도해 주세요.");
        }

        return ResponseEntity.ok().body(memberDto);
    }
}
