package com.example.demo.adapter.out.persistence;

import com.example.demo.application.port.out.FindMemberPort;
import com.example.demo.module.domain.member.respository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.PersistenceAdapter;
import org.springframework.transaction.annotation.Transactional;

@PersistenceAdapter
@RequiredArgsConstructor
@Slf4j
public class MemberPersistenceAdapter implements FindMemberPort {

    private final MemberRepository memberRepository;
    @Override
    public boolean existMember(long userId) {
        return memberRepository.existsById(userId);
    }
}
