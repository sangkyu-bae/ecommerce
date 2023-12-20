package com.example.demo.application.port.in.usecase;

import com.example.demo.application.port.in.command.FindMemberByAddressCommand;
import com.example.demo.application.port.in.command.FindMemberCommand;
import com.example.demo.module.domain.member.MemberVo;
import org.example.task.MemberTask;

import java.util.List;

public interface FindMemberUseCase {
    boolean existMember(FindMemberCommand command);
    List<MemberVo> findMemberListByAddress(FindMemberByAddressCommand command);
}
