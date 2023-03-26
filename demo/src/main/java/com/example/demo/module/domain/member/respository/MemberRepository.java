package com.example.demo.module.domain.member.respository;

import com.example.demo.module.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional(readOnly = true)
public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Object> findByEmailWithAuthority(String email);

    boolean existsByEmail(String email);
}
