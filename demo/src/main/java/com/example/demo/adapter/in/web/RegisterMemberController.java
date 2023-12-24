package com.example.demo.adapter.in.web;

import com.example.demo.adapter.in.request.RegisterMemberRequest;
import com.example.demo.application.port.in.command.RegisterMemberCommand;
import com.example.demo.application.port.in.usecase.RegisterMemberUseCase;
import com.example.demo.module.domain.member.MemberVo;
import com.example.demo.vaildator.RegisterMemberCommandValidator;
import lombok.RequiredArgsConstructor;
import org.example.WebAdapter;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@WebAdapter
public class RegisterMemberController {

    private final RegisterMemberCommandValidator validator;

    private final RegisterMemberUseCase registerMemberUseCase;

    @PostMapping("/user")
    public ResponseEntity<MemberVo> registerMember(@RequestBody RegisterMemberRequest request){

        RegisterMemberCommand command = RegisterMemberCommand.builder()
                .email(request.getEmail())
                .name(request.getName())
                .password(request.getPassword())
                .confirmPassword(request.getConfirmPassword())
                .build();

        Errors errors = new BeanPropertyBindingResult(command, "command");
        validator.validate(command, errors);

        if (errors.hasErrors()) {
            throw new IllegalArgumentException("회원가입이 실패하였습니다. 다시 시도해 주세요.");
        }


        MemberVo createMember = registerMemberUseCase.registerMember(command);

        return ResponseEntity.ok().body(createMember);
    }
}
