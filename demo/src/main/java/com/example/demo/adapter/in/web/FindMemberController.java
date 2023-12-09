package com.example.demo.adapter.in.web;

import com.example.demo.application.port.in.command.FindMemberByAddressCommand;
import com.example.demo.application.port.in.command.FindMemberCommand;
import com.example.demo.application.port.in.usecase.FindMemberUseCase;
import com.example.demo.module.domain.member.MemberVo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class FindMemberController {

    private final FindMemberUseCase findMemberUseCase;

    @GetMapping("/member/{addressName}")
    public ResponseEntity<List<MemberVo>> findMemberListByAddress(@PathVariable("addressName") String addressName){
        FindMemberByAddressCommand command = FindMemberByAddressCommand.builder()
                .addressName(addressName)
                .build();

        List<MemberVo> memberVoList = findMemberUseCase.findMemberListByAddress(command);

        return ResponseEntity.ok().body(memberVoList);
    }

    @GetMapping("/member/{memberId}")
    public ResponseEntity<Boolean> existMemberByMemberId(@PathVariable("memberId") long memberId){

        FindMemberCommand command = FindMemberCommand.builder()
                .userId(memberId)
                .build();
        boolean isMember = findMemberUseCase.existMember(command);
        return ResponseEntity.ok().body(isMember);
    }
}
