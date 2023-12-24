package com.example.demo.vaildator;

import com.example.demo.application.port.in.command.RegisterMemberCommand;
import com.example.demo.module.domain.member.respository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class RegisterMemberCommandValidator implements Validator {

    private final MemberRepository memberRepository;
    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(RegisterMemberCommand.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        RegisterMemberCommand command = (RegisterMemberCommand) target;

        if(!command.getPassword().equals(command.getConfirmPassword())){
            errors.rejectValue("confirmPassword","invalid.confirmPassword",new Object[]{command.getConfirmPassword()},"패스워드가 일치 하지 않습니다.");
        }
        if(memberRepository.existsByEmail(command.getEmail())){
            errors.rejectValue("email","invalid.email",new Object[]{command.getEmail()},"이미 존재하는 이메일 입니다.");
        }
    }
}
