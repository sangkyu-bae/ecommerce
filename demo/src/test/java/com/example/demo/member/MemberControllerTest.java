package com.example.demo.member;

import com.example.demo.module.domain.member.dto.JoinMemberDto;
import com.example.demo.module.domain.member.entity.Member;
import com.example.demo.module.domain.member.respository.MemberRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class MemberControllerTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    MemberRepository memberRepository;

    @Test
    @DisplayName(" 회원가입 - 입력값 정상")
    void joinMember() throws Exception{
        String email="test@test.com";
        String name="테스트";
        JoinMemberDto joinMemberDto=new JoinMemberDto();
        joinMemberDto.setPassword("1234abc!");
        joinMemberDto.setConfirmPassword("1234abc!");
        joinMemberDto.setEmail(email);
        joinMemberDto.setName(name);

        mockMvc.perform(post("/api/member/sign-up")
                .characterEncoding(StandardCharsets.UTF_8)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(joinMemberDto)))
                .andExpect(status().isOk())
                .andDo(print());

        Member member = memberRepository.findByEmail(email).orElseThrow();
        assertEquals(member.getName(),name);
    }

    @Test
    @DisplayName(" 회원가입 - 입력값 비정상")
    void errorJoinMember() throws Exception{
        String email="tes";
        String name="테스트";
        JoinMemberDto joinMemberDto=new JoinMemberDto();
        joinMemberDto.setPassword("1234abc!");
        joinMemberDto.setConfirmPassword("1234abc!");
        joinMemberDto.setEmail(email);
        joinMemberDto.setName(name);

        mockMvc.perform(post("/api/member/sign-up")
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(joinMemberDto)))
                .andExpect(status().isBadRequest());
    }
}