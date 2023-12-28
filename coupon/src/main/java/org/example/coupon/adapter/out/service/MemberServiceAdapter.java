package org.example.coupon.adapter.out.service;

import lombok.RequiredArgsConstructor;
import org.example.coupon.application.port.out.GetMemberPort;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MemberServiceAdapter implements GetMemberPort {
    private final MemberFeignClient memberFeignClient;

    @Override
    public List<Member> getMemberList() {
        List<Member> memberList = memberFeignClient.getMemberList();
        return memberList;
    }
}
