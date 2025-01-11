package com.example.order.application.service;

import com.example.order.adapter.axon.command.OrderRequestCreateCommand;
import com.example.order.adapter.out.external.delivery.DeliveryEvent;
import com.example.order.adapter.out.external.product.ProductInfoRequest;
import com.example.order.adapter.out.persistence.OrderMapper;
import com.example.order.adapter.out.persistence.entity.OrderEntity;
import com.example.order.application.port.in.command.RegisterOrderCd;
import com.example.order.application.port.in.command.RegisterOrderCommand;
import com.example.order.application.port.in.usecase.RegisterOrderUseCase;
import com.example.order.application.port.out.*;

import com.example.order.domain.OrderVo;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.example.CountDownLatchManager;
import org.example.task.MemberTask;
import org.example.task.OrderSubTask;
import org.example.UseCase;
import org.example.task.OrderTask;
import org.example.task.ProductTask;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@UseCase
@Slf4j
public class RegisterOrderService implements RegisterOrderUseCase {

    private final RegisterOrderPort registerOrderPort;
    private final OrderMapper orderMapper;
    private final RequestProductInfoPort requestProductInfoPort;
    private final CountDownLatchManager countDownLatchManager;
    private final SendCreateOrderTaskPort sendCreateOrderTaskPort;
    private final CommandGateway commandGateway;

    private final SendCreateDeliveryEventPort sendCreateDeliveryEventPort;

    @Override
    public OrderVo registerOrder(RegisterOrderCommand command) throws JsonProcessingException {
        //동기처리
//        Member member = getMemberPort.getMemberId(command.getUseremail());
        // 에외 처리 필요 ex member email이 없는 것이라면
        // 비동기 처리 kafka

        String MemberSubTaskName ="validMemberTask : 멤버십 유효성 검사";
        MemberTask validMemberTask =new MemberTask(
                command.getUserId(),
                UUID.randomUUID().toString(),
                MemberSubTaskName,
                OrderSubTask.Status.READY
                );
        String productSubTaskName = "validProductTask : 상품 유효성 검사";
        ProductTask validProductTask = new ProductTask(UUID.randomUUID().toString(),
                productSubTaskName,
                OrderSubTask.Status.READY,
                command.getProductId(),
                command.getColorId(),
                command.getAmount(),
                command.getSizeId()
                );


        List<OrderSubTask> orderSubTaskList = new ArrayList<>();
        orderSubTaskList.add(validMemberTask);
        orderSubTaskList.add(validProductTask);

        OrderTask orderTask = new OrderTask(UUID.randomUUID().toString(),
                "orderTask",
                orderSubTaskList);

        try {
            sendCreateOrderTaskPort.sendCreateOrderTask(orderTask);

            countDownLatchManager.addCountDownLatch(orderTask.getTaskId());
            countDownLatchManager.getCountDownLatch(orderTask.getTaskId()).await();
            String result = countDownLatchManager.getDataForKey(orderTask.getTaskId());
            if(result.equals("success")){
                OrderVo mapOrderVo = getOrderRequest(command);
                return mapOrderVo;
            }else{
                //오류 공통화작업 필요
                throw new Exception();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    @Transactional
    public OrderVo registerOrderByEvent(RegisterOrderCommand command) throws JsonProcessingException {

        String orderAggregateIdentifier = UUID.randomUUID().toString();
        OrderRequestCreateCommand axonCommand = new OrderRequestCreateCommand(
                orderAggregateIdentifier,
                command.getProductId(),
                command.getColorId(),
                command.getSizeId(),
                command.getAmount(),
                command.getPayment(),
                command.getAddress(),
                OrderVo.StatusCode.ORDER.getStatus(),
                command.getUserId(),
                command.getCouponId()
        );

        command.setAggregateIdentifier(orderAggregateIdentifier);

        commandGateway.send(axonCommand).whenComplete((result, throwable) -> {
            if (throwable != null) {
                log.error("throwable = " + throwable);
                throw new RuntimeException(throwable);
            } else{
                System.out.println("result = " + result);
                try {
                    getOrderRequest(command);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
        });

//        getOrderRequest(command);
        return null;
    }

    @Override
    public OrderVo registerOrderByEvent(List<RegisterOrderCd> command) throws JsonProcessingException {
        return null;
    }

    private OrderVo getOrderRequest(RegisterOrderCommand command) throws JsonProcessingException {
        int status = OrderVo.StatusCode.ORDER.getStatus();

        OrderVo createOrder = OrderVo.createGenerateOrderVo(
                new OrderVo.OrderId(0),
                new OrderVo.OrderProductUserId(command.getUserId()),
                new OrderVo.OrderProductId(command.getProductId()),
                new OrderVo.OrderColorId(command.getColorId()),
                new OrderVo.OrderSizeId(command.getSizeId()),
                new OrderVo.OrderAmount(command.getAmount()),
                new OrderVo.OrderPayment(command.getPayment()),
                new OrderVo.OrderAddress(command.getAddress()),
                new OrderVo.OrderCreateAt(LocalDateTime.now()),
                new OrderVo.OrderUpdateAt(LocalDateTime.now()),
                new OrderVo.OrderStatus(status),
                OrderVo.StatusCode.ORDER,
                new OrderVo.OrderAggregateIdentifier(command.getAggregateIdentifier()),
                new OrderVo.OrderSequence(command.getSequence())
        );

        OrderEntity createOrderEntity = registerOrderPort.createOrder(createOrder);
        OrderVo mapOrderVo = orderMapper.mapToDomainEntity(createOrderEntity);

        requestProductInfoPort.createOrderEvent(ProductInfoRequest
                .createGenerateProductRequest(
                        mapOrderVo.getProductId(),
                        mapOrderVo.getColorId(),
                        mapOrderVo.getAmount(),
                        mapOrderVo.getSizeId(),
                        mapOrderVo.getId()
                ));

        DeliveryEvent event = new DeliveryEvent(
                mapOrderVo.getSizeId(),
                mapOrderVo.getUserId(),
                mapOrderVo.getAddress(),
                mapOrderVo.getId()
        );
        sendCreateDeliveryEventPort.createDeliveryEvent(event);

//        responseDeliveryInfoPort.orderInformationToDelivery(OrderInfoRequest.createGenerateOrderRequest(command));
        return mapOrderVo;
    }
}
