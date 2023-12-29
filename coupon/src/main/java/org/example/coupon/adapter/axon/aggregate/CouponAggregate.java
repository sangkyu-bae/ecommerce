package org.example.coupon.adapter.axon.aggregate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;
import org.example.coupon.adapter.axon.command.CouponRequestCreateCommand;
import org.example.coupon.adapter.axon.event.CouponCreateEvent;
import org.example.event.CheckRegisteredCouponCommand;
import org.example.event.CheckRegisteredCouponEvent;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate()
@Data
@Slf4j
public class CouponAggregate {

    @AggregateIdentifier
    private String id;

    private Long createAdminId;

    private int salePercent;

    private String name;

    private LocalDateTime createAt;

    private List<CouponComponentAggregate> couponComponentAggregateList;


    @AllArgsConstructor
    public static class CouponComponentAggregate {

        private Long userId;

        private int status;

        private LocalDateTime endAt;
    }

    @CommandHandler
    public CouponAggregate(CouponRequestCreateCommand command) {
        log.info("CouponAggregate Sourcing Handler");
        List<CouponCreateEvent.CouponComponentCreateEvent>  couponComponentCreateEvents = command.getCouponComponentRequestCreateCommands().stream()
                        .map(couponComponentRequestCreateCommand -> new CouponCreateEvent.CouponComponentCreateEvent(
                                couponComponentRequestCreateCommand.getUserId(),
                                couponComponentRequestCreateCommand.getStatus(),
                                couponComponentRequestCreateCommand.getEndAt()
                        )).collect(Collectors.toList());

        apply(new CouponCreateEvent(
                command.getCreateAdminId(),
                command.getSalePercent(),
                command.getCreateAt(),
                command.getName(),
                couponComponentCreateEvents
        ));
    }

    @CommandHandler
    public void handler(CheckRegisteredCouponCommand command){
        log.info("CheckRegisteredCouponCommand Sourcing Handler!");
        /**
         * port 만들어서 true,fail 선택
         * Exeception 상황 시 
         * */
        apply(new CheckRegisteredCouponEvent(
                command.getCreateOrderId(),
                true,
                command.getCheckRegisteredCoupon(),
                command.getCouponId(),
                command.getProductSizeId(),
                command.getProductAmount()
        ));
    }

    @EventSourcingHandler
    public void on(CouponCreateEvent event) {
        log.info("CouponAggregate Sourcing Event");

        id = UUID.randomUUID().toString();
        createAt = event.getCreateAt();
        salePercent = event.getSalePercent();
        name = event.getName();
        createAdminId = event.getCreateAdminId();
        couponComponentAggregateList = event.getCouponComponentCreateEvents().stream()
                .map(couponComponentCreateEvent -> new CouponComponentAggregate(
                        couponComponentCreateEvent.getUserId(),
                        couponComponentCreateEvent.getStatus(),
                        couponComponentCreateEvent.getEndAt()
                )).collect(Collectors.toList());

    }


    public CouponAggregate() {

    }

}
