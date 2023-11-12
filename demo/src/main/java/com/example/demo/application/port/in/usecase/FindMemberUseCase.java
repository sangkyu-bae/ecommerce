package com.example.demo.application.port.in.usecase;

import com.example.demo.application.port.in.command.FindMemberCommand;
import org.example.task.MemberTask;

public interface FindMemberUseCase {
    boolean existMember(FindMemberCommand command);
}
