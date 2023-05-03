package com.example.demo.module.domain.member.service;

import com.example.demo.module.domain.member.dto.JoinMemberDto;
import com.example.demo.module.domain.member.entity.Member;
import com.example.demo.module.domain.member.respository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberWriteService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    public Member createMember(JoinMemberDto joinMemberDto){
        Member member=saveNewMember(joinMemberDto);
        return member;
    }

    private Member saveNewMember(JoinMemberDto joinMemberDto) {
        joinMemberDto.setPassword(passwordEncoder.encode(joinMemberDto.getPassword()));
        Member member=modelMapper.map(joinMemberDto,Member.class);
        member.createEmailCheckToken();
        return memberRepository.save(member);
    }

}
