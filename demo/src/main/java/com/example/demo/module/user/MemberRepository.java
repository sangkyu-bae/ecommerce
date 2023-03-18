package com.example.demo.module.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public class MemberRepository extends JpaRepository<Long,Member> {
}
