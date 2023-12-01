package com.example.demo.application.service;

import com.example.demo.application.port.in.command.FindMemberCommand;
import com.example.demo.application.port.in.usecase.FindMemberUseCase;
import com.example.demo.application.port.out.FindMemberPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.UseCase;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FindMemberService implements FindMemberUseCase {

    private final FindMemberPort findMemberPort;
    @Override
    public boolean existMember(FindMemberCommand command) {
        return findMemberPort.existMember(command.getUserId());
    }
}
