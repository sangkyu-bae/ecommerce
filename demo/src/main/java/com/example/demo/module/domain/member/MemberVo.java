package com.example.demo.module.domain.member;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class MemberVo {

    private final long userId;

    private final String email;

    private final String name;

    private final String address;

    private final boolean emailVerified;

    private final String emailCheckToken;

    private final String phone;

    private final boolean notificationByEmail;

    private final LocalDateTime joinAt;

    private final LocalDateTime updateAt;


    public static MemberVo createGenerateMemberVo(
           MemberUserId memberUserId,
           MemberEmail memberEmail,
           MemberName memberName,
           MemberAddress memberAddress,
           MemberEmailVerified memberEmailVerified,
           MemberEmailCheckToken memberEmailCheckToken,
           MemberPhone memberPhone,
           MemberNotificationByEmail memberNotificationByEmail,
           MemberJoinAt memberJoinAt,
           MemberUpdateAt memberUpdateAt
    ){
        return new MemberVo(
                memberUserId.getUserId(),
                memberEmail.getEmail(),
                memberName.getName(),
                memberAddress.getAddress(),
                memberEmailVerified.emailVerified,
                memberEmailCheckToken.getEmailCheckToken(),
                memberPhone.getPhone(),
                memberNotificationByEmail.notificationByEmail,
                memberJoinAt.getJoinAt(),
                memberUpdateAt.getUpdateAt()
        );
    }

    @Value
    public static class MemberUserId{
        long userId;

        public MemberUserId(long value){
            this.userId = value;
        }
    }
    @Value
    public static class MemberEmail{
        String email;

        public MemberEmail(String value){
            this.email = value;
        }
    }
    @Value
    public static class MemberName{
        String name;

        public MemberName(String value){
            this.name = value;
        }
    }
    @Value
    public static class MemberAddress{
        String address;

        public MemberAddress(String value){
            this.address = value;
        }
    }
    @Value
    public static class MemberEmailVerified{
        boolean emailVerified;

        public MemberEmailVerified(boolean value){
            this.emailVerified = value;
        }
    }
    @Value
    public static class MemberEmailCheckToken{
        String emailCheckToken;

        public MemberEmailCheckToken(String value){
            this.emailCheckToken = value;
        }
    }
    @Value
    public static class MemberPhone{
        String phone;

        public MemberPhone(String value){
            this.phone = value;
        }
    }
    @Value
    public static class MemberNotificationByEmail{
        boolean notificationByEmail;

        public MemberNotificationByEmail(boolean value){
            this.notificationByEmail = value;
        }
    }
    @Value
    public static class MemberJoinAt{
        LocalDateTime joinAt;

        public MemberJoinAt(LocalDateTime value){
            this.joinAt = value;
        }
    }
    @Value
    public static class MemberUpdateAt {
        LocalDateTime updateAt;

        public MemberUpdateAt(LocalDateTime value){
            this.updateAt = value;
        }
    }

}
