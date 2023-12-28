package org.example.coupon.application.port.out;

import org.example.coupon.adapter.out.service.Member;

import java.util.List;

public interface GetMemberPort {

    List<Member> getMemberList();
}
