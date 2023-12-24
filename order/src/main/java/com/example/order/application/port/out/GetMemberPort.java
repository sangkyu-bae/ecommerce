package com.example.order.application.port.out;

import com.example.order.adapter.out.service.Member;

public interface GetMemberPort {
    boolean getIsMember(long userId);

    Member getMember(long userId);
}
