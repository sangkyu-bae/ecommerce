package com.example.demo.module.domain.member;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    private final Long userId;

    private final String email;

    @JsonIgnore
    private final String password;

    private final String name;

    private final String address;

    private final boolean emailVerified;

    private final String emailCheckToken;

    private final String phone;

    private final boolean notificationByEmail;

    private final LocalDateTime joinAt;

    private final LocalDateTime updateAt;

    private final String aggregateIdentifier;


    public static MemberVo createGenerateMemberVo(
           MemberUserId memberUserId,
           MemberEmail memberEmail,
           MemberPassword memberPassword,
           MemberName memberName,
           MemberAddress memberAddress,
           MemberEmailVerified memberEmailVerified,
           MemberEmailCheckToken memberEmailCheckToken,
           MemberPhone memberPhone,
           MemberNotificationByEmail memberNotificationByEmail,
           MemberJoinAt memberJoinAt,
           MemberUpdateAt memberUpdateAt,
           MemberAggregateIdentifier memberAggregateIdentifier
    ){
        return new MemberVo(
                memberUserId.getUserId(),
                memberEmail.getEmail(),
                memberPassword.getPassword(),
                memberName.getName(),
                memberAddress.getAddress(),
                memberEmailVerified.emailVerified,
                memberEmailCheckToken.getEmailCheckToken(),
                memberPhone.getPhone(),
                memberNotificationByEmail.notificationByEmail,
                memberJoinAt.getJoinAt(),
                memberUpdateAt.getUpdateAt(),
                memberAggregateIdentifier.getAggregateIdentifier()
        );
    }

    @Value
    public static class MemberUserId{
        Long userId;

        public MemberUserId(Long value){
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
    public static class MemberPassword{
        String password;

        public MemberPassword(String value){
            this.password = value;
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

    @Value
    public static class MemberAggregateIdentifier{
        String aggregateIdentifier;

        public MemberAggregateIdentifier(String value){
            this.aggregateIdentifier= value;
        }
    }

}
