package com.example.order.adapter.axon.saga;

import com.example.order.adapter.axon.event.RegisterOrderCreatedEvent;
import com.example.order.adapter.out.service.Coupon;
import com.example.order.adapter.out.service.Member;
import com.example.order.adapter.out.service.Product;
import com.example.order.application.port.out.GetCouponPort;
import com.example.order.application.port.out.GetMemberPort;
import com.example.order.application.port.out.GetProductPort;
import com.example.order.application.port.out.SendCreateDeliveryEventPort;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.saga.EndSaga;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.SagaLifecycle;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;
import org.example.event.*;
import org.example.event.rollback.RollbackProductFinishedEvent;
import org.example.event.rollback.RollbackRequestProductCommand;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

@Saga
@NoArgsConstructor
@Slf4j
public class OrderCreateSaga {
    @NonNull
    private transient CommandGateway commandGateway;

    @Autowired
    private void setCommandGateway(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @StartSaga
    @SagaEventHandler(associationProperty = "createOrderId")
    public void handle(RegisterOrderCreatedEvent event, GetMemberPort getMemberPort) {

        log.info("RegisterOrderCreatedEvent Start Saga");

        Member member = getMemberPort.getMember(event.getUserId());

        String checkRegisteredMemberId = UUID.randomUUID().toString();
        SagaLifecycle.associateWith("checkRegisteredMemberId", checkRegisteredMemberId);

        //"주문 생성" 시작
        // 멤버의 실제 멤버 여부 확인하기.
        // -> axon server -> member Service
        CheckRegisteredMemberCommand command = new CheckRegisteredMemberCommand(
                member.getAggregateIdentifier(),
                event.getCreateOrderId(),
                checkRegisteredMemberId,
                event.getProductId(),
                event.getSizeId(),
                event.getAmount(),
                event.getPayment(),
                event.getAddress(),
                event.getStatus(),
                event.getUserId(),
                event.getCouponId()
        );
        commandGateway.send(command).whenComplete(
                (result, throwable) -> {
                    if (throwable != null) {
                        throwable.printStackTrace();
                        log.error("CheckRegisteredMemberCommand Command failed");
                    } else {
                        log.info("CheckRegisteredMemberCommand Command success");
                    }
                }
        );
    }

    @SagaEventHandler(associationProperty = "checkRegisteredMemberId")
    public void handle(CheckRegisteredMemberEvent event, GetProductPort getProductPort) {
        log.info("CheckedRegisteredMember saga: " + event.toString());
        boolean status = event.isStatus();
        if (status) {
            log.info("CheckedRegisteredMember event success");
        } else {
            log.error("CheckedRegisteredMember event Failed");
        }

        Product findProduct = getProductPort.getProduct(event.getProductId());

        log.info("find product : " + findProduct.toString());
        String checkRegisteredProductIdAndAmount = UUID.randomUUID().toString();
        SagaLifecycle.associateWith("checkRegisteredProductIdAndAmount", checkRegisteredProductIdAndAmount);

        CheckRegisteredProductCommand command = new CheckRegisteredProductCommand(
                findProduct.getAggregateIdentifier(),
                event.getCreateOrderId(),
                checkRegisteredProductIdAndAmount,
                event.getProductId(),
                event.getSizeId(),
                event.getAmount(),
                event.getCouponId(),
                event.getMemberId()
        );

        commandGateway.send(command).whenComplete(
                (result, throwable) -> {
                    if (throwable != null) {
                        throwable.printStackTrace();
                        log.error("CheckRegisteredProductCommand Command failed");
                    } else {
                        log.info("CheckRegisteredProductCommand Command success");
                    }
                }
        );
    }

    @SagaEventHandler(associationProperty = "checkRegisteredProductIdAndAmount")
    public void handle(CheckRegisteredProductEvent event, GetCouponPort getCouponPort) {
       if(event.isSuccess()){
           if(event.getCouponId() == null){
               SagaLifecycle.end();
           }else{

               Coupon coupon = getCouponPort.getCoupon(event.getCouponId());

               String checkRegisteredCoupon = UUID.randomUUID().toString();
               SagaLifecycle.associateWith("checkRegisteredCoupon", checkRegisteredCoupon);

               CheckRegisteredCouponCommand command = new CheckRegisteredCouponCommand(
                       coupon.getAggregateIdentifier(),
                       event.getCreateOrderId(),
                       checkRegisteredCoupon,
                       event.getCouponId(),
                       event.getSizeId(),
                       event.getAmount(),
                       128,
                       event.getProductAggregate()
               );

               commandGateway.send(command).whenComplete(
                       (result, throwable) -> {
                           if (throwable != null) {
                               throwable.printStackTrace();
                               log.error("CheckRegisteredCouponCommand Command failed");
                           } else {
                               log.info("CheckRegisteredCouponCommand Command success");
                           }
                       }
               );
           }
       }else{
           //에러 처리
       }
    }

    @SagaEventHandler(associationProperty = "checkRegisteredCoupon")
    public void handel(CheckRegisteredCouponEvent event){
        log.info("?? {}",event);
        if(event.isSuccess()){
            System.out.println("end saga");
            SagaLifecycle.end();
        }else{
            String rollbackProductId = UUID.randomUUID().toString();
            SagaLifecycle.associateWith("rollbackProductId", rollbackProductId);

            RollbackRequestProductCommand command = new RollbackRequestProductCommand(
                    event.getProductAggregate(),
                    event.getProductSizeId(),
                    event.getProductAmount(),
                    rollbackProductId
            );

            commandGateway.send(command).whenComplete(
                    (result, throwable) -> {
                        if (throwable != null) {
                            throwable.printStackTrace();
                            log.error("RollbackRequestProductCommand Command failed");
                        } else {
                            log.info("RollbackRequestProductCommand Command success");
                        }
                    }
            );
        }
    }

    @SagaEventHandler(associationProperty = "rollbackProductId")
    @EndSaga
    public void handle(RollbackProductFinishedEvent event){
        log.info("end Saga event : {}", event);
    }



}
