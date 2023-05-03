package com.example.demo.module.domain.member.validatior;

import com.example.demo.module.domain.member.dto.JoinMemberDto;
import com.example.demo.module.domain.member.respository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class JoinMemberDtoValidator implements Validator {
    private final MemberRepository memberRepository;
    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(JoinMemberDto.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        JoinMemberDto joinMemberDto=(JoinMemberDto) target;

        if(!joinMemberDto.getPassword().equals(joinMemberDto.getConfirmPassword())){
             errors.rejectValue("confirmPassword","invalid.confirmPassword",new Object[]{joinMemberDto.getConfirmPassword()},"패스워드가 일치 하지 않습니다.");
        }
        if(memberRepository.existsByEmail(joinMemberDto.getEmail())){
            errors.rejectValue("email","invalid.email",new Object[]{joinMemberDto.getEmail()},"이미 존재하는 이메일 입니다.");
        }
    }
}
