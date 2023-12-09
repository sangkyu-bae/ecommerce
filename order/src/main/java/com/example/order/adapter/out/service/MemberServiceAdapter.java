package com.example.order.adapter.out.service;

import com.example.order.application.port.out.GetMemberPort;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberServiceAdapter implements GetMemberPort {

    private final MemberFeignClient memberFeignClient;
    @Override
    public boolean getMember(long memberId) {
        boolean isMember = memberFeignClient.getIsMember(memberId);
        return isMember;
    }
}
