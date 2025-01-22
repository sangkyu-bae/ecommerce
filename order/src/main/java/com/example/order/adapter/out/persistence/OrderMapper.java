package com.example.order.adapter.out.persistence;

import com.example.order.adapter.out.persistence.entity.OrderEntity;
import com.example.order.adapter.out.persistence.entity.ProductEntity;
import com.example.order.domain.OrderAggregationVo;
import com.example.order.domain.OrderVo;
import com.example.order.domain.Product;
import com.example.order.domain.SearchOrder;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class OrderMapper {

    private final ModelMapper modelMapper;

    private final ProductMapper productMapper;

    public OrderVo mapToDomainEntity(OrderEntity orderEntity){
        OrderVo.StatusCode statusCode = OrderVo.StatusCode.findStatusCode(orderEntity.getStatus());
        List<ProductEntity> productEntityList = orderEntity.getProductList();

        List<Product> productList = new ArrayList<>();
        for(ProductEntity productEntity : productEntityList){
            Product product = productMapper.mapToDomain(productEntity);
            productList.add(product);
        }

        OrderVo orderVo = OrderVo.createGenerateOrderVo(
                new OrderVo.OrderId(orderEntity.getId()),
                new OrderVo.OrderProductUserId(orderEntity.getUserId()),
                new OrderVo.OrderPayment(orderEntity.getPayment()),
                new OrderVo.OrderAddress(orderEntity.getAddress()),
                new OrderVo.OrderPhoneNumber(orderEntity.getPhoneNumber()),
                new OrderVo.OrderCreateAt(orderEntity.getCreateAt()),
                new OrderVo.OrderUpdateAt(orderEntity.getUpdateAt()),
                new OrderVo.OrderStatus(orderEntity.getStatus()),
                statusCode,
                new OrderVo.OrderAggregateIdentifier(orderEntity.getAggregateIdentifier()),
                productList
        );

        return orderVo;
    }

    public SearchOrder mapToSearch(
                                            List<OrderAggregationVo> orderAggregationVos,
                                            Page<OrderEntity> orderPage
                                   ){

        SearchOrder searchOrder = SearchOrder.createGenerate(
                orderAggregationVos,
                orderPage.getNumber(),
                orderPage.getSize(),
                orderPage.getTotalElements(),
                orderPage.getTotalPages()
        );

        return searchOrder;
    }
}
