package com.example.order.adapter.in.web;


import com.example.order.adapter.in.request.RegisterOrderProductRequest;
import com.example.order.adapter.in.request.RegisterOrderRequest;
import com.example.order.adapter.in.request.RegisterOrderRq;
import com.example.order.adapter.out.persistence.entity.OrderEntity;
import com.example.order.adapter.out.persistence.repository.OrderEntityRepository;
import com.example.order.application.port.in.command.RegisterOrderCd;
import com.example.order.application.port.in.command.RegisterOrderCommand;
import com.example.order.application.port.in.usecase.RegisterOrderUseCase;
import com.example.order.domain.OrderVo;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.WebAdapter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@WebAdapter
public class RegisterOrderController {

    private final RegisterOrderUseCase registerOrderUseCase;

    private final OrderEntityRepository entityRepository;
//    @Operation(summary = "register order with kafka", description = "kafka로 주문 등록하기 (쓰레드 락)")
//    @PostMapping("/order/register/kafka")
//    public ResponseEntity<OrderVo> registerOrderByKafka(@RequestBody RegisterOrderRequest request ,
//                                                 @RequestHeader("X-User-Id") Long userId) throws JsonProcessingException {
//
//
//
//        RegisterOrderProductRequest req = request.getProducts().get(0);
//
//        RegisterOrderCommand command = RegisterOrderCommand.builder()
//                .productId(req.getProductId())
//                .colorId(req.getColorId())
//                .sizeId(req.getSizeId())
//                .amount(req.getAmount())
//                .payment(req.getPayment())
////                .status(RegisterOrderCommand.StatusCode.ORDER.getStatus())
//                .userId(userId)
//                .build();
//
//        OrderVo orderVo = registerOrderUseCase.registerOrder(command);
//
//        return ResponseEntity.ok().body(orderVo);
//    }

    @Operation(summary = "register order with axon", description = "axon 주문 등록하기 (saga구현 eda)")
    @PostMapping("/order/register/axon")
    public ResponseEntity<OrderVo> registerOrderByAxon(@RequestBody RegisterOrderRequest request ,
                                                 @RequestHeader("X-User-Id") Long userId)  {


        List<RegisterOrderCommand.ProductCommand> productCommands =new ArrayList<>();

        for(RegisterOrderProductRequest requestProduct : request.getProducts()){

            RegisterOrderCommand.ProductCommand productCommand = RegisterOrderCommand.ProductCommand.builder()
                    .productId(requestProduct.getProductId())
                    .colorId(requestProduct.getColorId())
                    .sizeId(requestProduct.getSizeId())
                    .amount(requestProduct.getAmount())
                    .couponId(requestProduct.getCouponId())
                    .build();

            productCommands.add(productCommand);
        }


        RegisterOrderCommand command = RegisterOrderCommand.builder()
                .payment(request.getPayment())
                .address(request.getAddress())
                .userId(userId)
                .aggregateIdentifier(String.valueOf(UUID.randomUUID()))
                .phone(request.getPhone())
                .productCommands(productCommands)
                .build();

        OrderVo orderVo = registerOrderUseCase.registerOrderByEvent(command);

        return ResponseEntity.ok().body(orderVo);
    }


    @Operation(summary = "register order", description = "axon 주문 등록하기 (saga구현 eda)")
    @PostMapping("/order/register")
    public ResponseEntity<OrderVo> registerOrder(@RequestBody RegisterOrderRequest request,
                                                       @RequestHeader("X-User-Id") Long userId ) throws JsonProcessingException {
        List<RegisterOrderProductRequest> productRequests = request.getProducts();
        List<RegisterOrderCommand.ProductCommand> productCommands = new ArrayList<>();

        for(RegisterOrderProductRequest rq : productRequests){
            RegisterOrderCommand.ProductCommand productCommand = RegisterOrderCommand.ProductCommand.builder()
                    .productId(rq.getProductId())
                    .productName(rq.getProductName())
                    .couponId(rq.getCouponId())
                    .sizeId(rq.getSizeId())
                    .colorId(rq.getColorId())
                    .amount(rq.getAmount())
                    .build();
            productCommands.add(productCommand);
        }


        String aggregateIdentifier = UUID.randomUUID().toString();
        RegisterOrderCommand command = RegisterOrderCommand.builder()
                .payment(request.getPayment())
                .address(request.getAddress())
                .userId(userId)
                .phone(request.getPhone())
                .productCommands(productCommands)
                .aggregateIdentifier(aggregateIdentifier)
                .build();

        OrderVo orderVo = registerOrderUseCase.registerOrderByEvent(command);

        return ResponseEntity.ok().body(orderVo);

    }

    @PostMapping("/order/test-bulk")
    public void registerOrderTestData(){
        List<OrderEntity> orderEntities = new ArrayList<>();

        /**
         * test 데이터 생성을 위한 하드 코딩 추후 변경 필요
         * */
        Map<Integer,List<Integer>> productColorOneIdMap = Map.of(
                3, Arrays.asList(5,19,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50)
        );

        Map<Integer,List<Integer>> productColorTwoIdMap = Map.of(
                3,Arrays.asList(18,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35)
        );

//        for(int i = 0 ; i < 50000; i++){
//
//            OrderEntity orderEntity = OrderEntity.builder()
//                    .productId()
//                    .userId()
//                    .colorId()
//                    .sizeId()
//                    .amount()
//                    .payment()
//                    .status()
//                    .aggregateIdentifier()
//                    .build();
//        }
    }
}
