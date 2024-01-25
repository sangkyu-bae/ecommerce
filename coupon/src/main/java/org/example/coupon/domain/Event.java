package org.example.coupon.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;

import java.time.LocalDateTime;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class Event {

    private final Long id;

    private final String couponName;

    private final int salePercent;

    private final int quantity;

    private final LocalDateTime startAt;

    private final LocalDateTime endAt;

    public static Event createGenerateEvent(
            EventId eventId,
            EventCouponName eventCouponName,
            EventSalePercent eventSalePercent,
            EventQuantity eventQuantity,
            EventStartAt eventStartAt,
            EventEndAt eventEndAt
    ){
        return new Event(
                eventId.getId(),
                eventCouponName.getCouponName(),
                eventSalePercent.getSalePercent(),
                eventQuantity.getQuantity(),
                eventStartAt.getStartAt(),
                eventEndAt.getEndAt()
        );
    }

    @Value
    public static class EventId{

        public EventId(Long value){
            this.id = value;
        }

        private Long id;
    }

    @Value
    public static class EventCouponName{

        public EventCouponName(String value){
            this.couponName = value;
        }

        private String couponName;
    }
    @Value
    public static class EventSalePercent{

        public EventSalePercent(int value){
            this.salePercent = value;
        }

        private int salePercent;
    }

    @Value
    public static class EventQuantity{

        public EventQuantity(int value){
            this.quantity = value;
        }

        private int quantity;
    }

    @Value
    public static class EventStartAt {

        public EventStartAt(LocalDateTime value){
            this.startAt = value;
        }

        private LocalDateTime startAt;
    }

    @Value
    public static class EventEndAt {

        public EventEndAt(LocalDateTime value){
            this.endAt = value;
        }

        private LocalDateTime endAt;
    }
}
