package com.example.order.application.service;

import com.example.order.adapter.out.external.product.ProductInfoRequest;
import com.example.order.adapter.out.persistence.OrderMapper;
import com.example.order.adapter.out.persistence.entity.OrderEntity;
import com.example.order.application.port.in.command.RegisterOrderCommand;
import com.example.order.application.port.in.usecase.RegisterOrderUseCase;
import com.example.order.application.port.out.*;

import com.example.order.domain.OrderVo;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.CountDownLatchManager;
import org.example.task.MemberTask;
import org.example.task.OrderSubTask;
import org.example.UseCase;
import org.example.task.OrderTask;
import org.example.task.ProductTask;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@UseCase
@Slf4j
public class RegisterOrderService implements RegisterOrderUseCase {

    private final RegisterOrderPort registerOrderPort;
    private final OrderMapper orderMapper;
    private final ResponseDeliveryInfoPort responseDeliveryInfoPort;
    private final RequestProductInfoPort requestProductInfoPort;
    private final GetMemberPort getMemberPort;
    private final CountDownLatchManager countDownLatchManager;

    private final SendCreateOrderTaskPort sendCreateOrderTaskPort;

    @Override
    public OrderVo registerOrder(RegisterOrderCommand command) throws JsonProcessingException {
        //동기처리
//        Member member = getMemberPort.getMemberId(command.getUseremail());
        // 에외 처리 필요 ex member email이 없는 것이라면
        // 비동기 처리 kafka

        System.out.println("registerOrder : " +command.getUserId());
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
            System.out.println("orderTaskId:" + orderTask.getTaskId());

            countDownLatchManager.addCountDownLatch(orderTask.getTaskId());
            countDownLatchManager.getCountDownLatch(orderTask.getTaskId()).await();
            String result = countDownLatchManager.getDataForKey(orderTask.getTaskId());
            System.out.println(result);
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

    private OrderVo getOrderRequest(RegisterOrderCommand command) throws JsonProcessingException {
        int status = OrderVo.StatusCode.ORDER.getStatus();

        OrderVo createOrder = OrderVo.createGenerateOrderVo(
                new OrderVo.OrderId(0),
                new OrderVo.OrderProductId(command.getProductId()),
                new OrderVo.OrderColorId(command.getColorId()),
                new OrderVo.OrderSizeId(command.getSizeId()),
                new OrderVo.OrderAmount(command.getAmount()),
                new OrderVo.OrderPayment(command.getPayment()),
                new OrderVo.OrderAddress(command.getAddress()),
                new OrderVo.OrderCreateAt(LocalDate.now()),
                new OrderVo.OrderUpdateAt(LocalDate.now()),
                new OrderVo.OrderStatus(status)
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

//        responseDeliveryInfoPort.orderInformationToDelivery(OrderInfoRequest.createGenerateOrderRequest(command));
        return mapOrderVo;
    }
}
