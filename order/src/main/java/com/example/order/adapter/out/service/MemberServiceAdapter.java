package com.example.order.adapter.out.service;

import com.example.order.application.port.out.GetMemberPort;
import com.example.order.application.port.out.Member;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;

import org.example.CommonHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceAdapter implements GetMemberPort {

    private final CommonHttpClient commonHttpClient;

    private final String memberServiceUrl;

    public MemberServiceAdapter(CommonHttpClient commonHttpClient,
                                @Value("${member.service.url}") String memberServiceUrl){
        this.commonHttpClient = commonHttpClient;
        this.memberServiceUrl = memberServiceUrl;
    }
    @Override
    public Member getMemberId(String email) {

        String url = String.join("/",memberServiceUrl,"member",email);
        try {
            String memberRes = commonHttpClient.sendGetRequest(url).body();
            ObjectMapper mapper =new ObjectMapper();
            Member member = mapper.readValue(memberRes, Member.class);

            return member;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
