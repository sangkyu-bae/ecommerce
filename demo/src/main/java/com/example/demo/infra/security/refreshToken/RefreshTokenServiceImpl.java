package com.example.demo.infra.security.refreshToken;

import com.example.demo.module.domain.member.entity.Member;
import com.example.demo.module.domain.member.respository.MemberRepository;
import com.example.demo.module.domain.redis.entity.RefreshToken;

import com.example.demo.module.domain.redis.repository.RefreshTokenRedisRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.webjars.NotFoundException;

import java.util.Optional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
@Service
public class RefreshTokenServiceImpl implements RefreshTokenService{
    private final MemberRepository memberRepository;
    private final RefreshTokenRedisRepository refreshTokenRedisRepository;
    @Override
    @Transactional
    public void updateRefreshToken(String email, String uuid) {
        Member member=memberRepository.findByEmail(email)
                .orElseThrow(()->new NotFoundException("id :" + email + "는 없는 사용자 입니다."));
        refreshTokenRedisRepository.save(RefreshToken.of(String.valueOf(member.getUserId()), uuid));
    }

    @Override
    public boolean existRefreshToken(String userId,String refreshToken) {
        //예외처리 필요
        RefreshToken token = refreshTokenRedisRepository.findById(userId).orElseThrow();

        if(!token.getRefreshTokenId().equals(refreshToken)){
            return false;
        }

        return true;
    }
}
