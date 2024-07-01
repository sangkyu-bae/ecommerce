package com.example.order.application.service;

import com.example.order.adapter.out.persistence.OrderMapper;
import com.example.order.adapter.out.persistence.entity.OrderEntity;
import com.example.order.adapter.out.service.Product;
import com.example.order.application.port.in.command.FindMemberOrderListByMemberIdsCommand;
import com.example.order.application.port.in.command.FindOrderByMemberIdCommand;
import com.example.order.application.port.in.command.FindOrderCommand;
import com.example.order.application.port.in.usecase.FindOrderUseCase;
import com.example.order.application.port.out.FindOrderPort;

import com.example.order.application.port.out.GetMemberOrderPort;
import com.example.order.application.port.out.GetProductPort;
import com.example.order.domain.OrderAggregationVo;
import com.example.order.domain.OrderVo;
import com.example.order.domain.TypeEnumMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.UseCase;
import org.springframework.data.domain.Sort;

import java.util.*;
import java.util.stream.Collectors;

@UseCase
@Slf4j
@RequiredArgsConstructor
public class FindOrderService implements FindOrderUseCase {

    private final FindOrderPort findOrderPort;
    private final OrderMapper orderMapper;

    private final GetMemberOrderPort getMemberOrderPort;

    private final GetProductPort getProductPort;


    @Override
    public OrderVo findOrder(FindOrderCommand command) {
        OrderVo.OrderId orderId = new OrderVo.OrderId(command.getOrderId());
        OrderEntity orderEntity = findOrderPort.findOrder(orderId);

        return orderMapper.mapToDomainEntity(orderEntity);
    }

    @Override
    public List<OrderVo> findMemberOrderListByMemberIds(FindMemberOrderListByMemberIdsCommand command) {
        List<OrderEntity> orders = getMemberOrderPort.getMemberOrderPort(command.getMemberIds());

        List<OrderVo> orderVoList = orders.stream()
                .map(order -> orderMapper.mapToDomainEntity(order))
                .collect(Collectors.toList());


        return orderVoList;
    }

    @Override
    public List<OrderAggregationVo> findOrderListByMemberId(FindOrderByMemberIdCommand command) {
        List<OrderEntity> orderEntityList = findOrderPort.findOrderByMemberId(new OrderVo.OrderProductUserId(command.getUserId()));

        Set<Long> productIdsSet = orderEntityList.stream()
                .map(OrderEntity::getProductId)
                .collect(Collectors.toSet());

        List<Long> productIds = new ArrayList<>(productIdsSet);
        List<Product> productList = getProductPort.getProductListByProductIds(productIds);

        List<OrderAggregationVo> orderAggregationVos = new ArrayList<>();
        Map<Long,Product> productMap = new HashMap<>();

        for(Product product : productList){
            productMap.put(product.getId(),product);
        }

        for(OrderEntity orderEntity : orderEntityList){

            Product product = productMap.get(orderEntity.getProductId());
            Product cleanProduct = Product.cleanProduct(product);
            OrderVo.StatusCode statusCode = OrderVo.StatusCode.findStatusCode(orderEntity.getStatus());

            OrderAggregationVo orderAggregationVo = OrderAggregationVo.createGenerate(
                    orderEntity.getId(),
                    orderEntity.getAmount(),
                    orderEntity.getPayment(),
                    cleanProduct.getProductComponents().get(0).getSizes().size(),
                    cleanProduct.getProductImage(),
                    cleanProduct.getDescription(),
                    cleanProduct.getName(),
                    cleanProduct.getBrand().getName(),
                    cleanProduct.getProductComponents().get(0).getColor().getName(),
                    new TypeEnumMapper(statusCode)
            );
            orderAggregationVos.add(orderAggregationVo);
        }

//        return orderEntityList.stream()
//                .map(order -> orderMapper.mapToDomainEntity(order))
//                .collect(Collectors.toList());
        return orderAggregationVos;
    }
}
