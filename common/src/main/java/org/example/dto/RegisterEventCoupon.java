package org.example.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor @NoArgsConstructor
public class RegisterEventCoupon {

    private long userId;
    private long eventId;

    public static RegisterEventCoupon createGenerate(long userId,long eventId){
        return new RegisterEventCoupon(userId,eventId);
    }
}
