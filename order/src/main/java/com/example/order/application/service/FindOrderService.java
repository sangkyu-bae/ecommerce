package com.example.order.application.service;

import com.example.order.adapter.out.persistence.OrderMapper;
import com.example.order.adapter.out.persistence.entity.OrderEntity;
import com.example.order.adapter.out.service.Product;
import com.example.order.application.port.in.command.FindMemberOrderListByMemberIdsCommand;
import com.example.order.application.port.in.command.FindOrderByMemberIdCommand;
import com.example.order.application.port.in.command.FindOrderByMemberPagingCommand;
import com.example.order.application.port.in.command.FindOrderCommand;
import com.example.order.application.port.in.usecase.FindOrderUseCase;
import com.example.order.application.port.out.FindOrderPort;

import com.example.order.application.port.out.GetMemberOrderPort;
import com.example.order.application.port.out.GetProductPort;
import com.example.order.domain.OrderAggregationVo;
import com.example.order.domain.OrderVo;
import com.example.order.domain.SearchOrder;
import com.example.order.domain.TypeEnumMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.UseCase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

        List<OrderAggregationVo> orderAggregationVos = getOrderAggregationVos(orderEntityList);

//        return orderEntityList.stream()
//                .map(order -> orderMapper.mapToDomainEntity(order))
//                .collect(Collectors.toList());
        return orderAggregationVos;
    }

    @Override
    public SearchOrder findOrderByMemberIdPaging(FindOrderByMemberPagingCommand command) {
        Pageable pageable = PageRequest.of(command.getPageNum() - 1, 10, Sort.Direction.ASC,"id");
        Page<OrderEntity> orderEntities = findOrderPort.findOrderByMemberIdPaging(
                new OrderVo.OrderProductUserId(command.getUserId())
                ,pageable
        );

        List<OrderAggregationVo> orderAggregationVos = getOrderAggregationVos(orderEntities.toList());

        return orderMapper.mapToSearch(orderAggregationVos,orderEntities);
    }


    private List<OrderAggregationVo> getOrderAggregationVos(List<OrderEntity> orderEntityList) {
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
            cleanProduct.settingComponent(orderEntity.getColorId(),orderEntity.getSizeId());
            OrderVo.StatusCode statusCode = OrderVo.StatusCode.findStatusCode(orderEntity.getStatus());

            OrderAggregationVo orderAggregationVo = OrderAggregationVo.createGenerate(
                    orderEntity.getId(),
                    orderEntity.getAmount(),
                    orderEntity.getPayment(),
                    cleanProduct.getProductComponents().get(0).getSizes().get(0).getSize(),
                    cleanProduct.getProductImage(),
                    cleanProduct.getDescription(),
                    cleanProduct.getName(),
                    cleanProduct.getBrand().getName(),
                    cleanProduct.getProductComponents().get(0).getColor().getName(),
                    new TypeEnumMapper(statusCode)
            );
            orderAggregationVos.add(orderAggregationVo);
        }
        return orderAggregationVos;
    }

}
