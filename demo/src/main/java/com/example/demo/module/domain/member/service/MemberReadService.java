package com.example.demo.module.domain.member.service;

import com.example.demo.module.domain.member.dto.MemberDto;
import com.example.demo.module.domain.member.entity.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UnknownFormatConversionException;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberReadService {

    public MemberDto toMemberDto(Member member){
        MemberDto memberDto=null;
        try{
            memberDto=new MemberDto(member);
        }catch (Exception exception){
            log.error("UnknownFormatConversionException toMemberDto()");
            throw new UnknownFormatConversionException("could Not Convert Member to MemberDto");
        }
        return memberDto;
    }
}
