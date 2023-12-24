package com.example.demo.application.port.in.usecase;

import com.example.demo.application.port.in.command.RegisterMemberCommand;
import com.example.demo.module.domain.member.MemberVo;

public interface RegisterMemberUseCase {

    MemberVo registerMember(RegisterMemberCommand command);
}
