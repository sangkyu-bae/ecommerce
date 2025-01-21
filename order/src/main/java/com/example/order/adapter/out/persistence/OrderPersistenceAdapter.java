package com.example.order.adapter.out.persistence;

import com.example.order.adapter.out.persistence.entity.OrderEntity;
import com.example.order.adapter.out.persistence.entity.ProductEntity;
import com.example.order.adapter.out.persistence.repository.OrderEntityRepository;
import com.example.order.adapter.out.persistence.repository.ProductEntityRepository;
import com.example.order.application.port.out.FindOrderPort;
import com.example.order.application.port.out.GetMemberOrderPort;
import com.example.order.application.port.out.RegisterOrderPort;
import com.example.order.application.port.out.RemoveOrderPort;
import com.example.order.domain.Product;
import com.example.order.infra.error.ErrorException;
import com.example.order.infra.error.OrderErrorCode;
import com.example.order.domain.OrderVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.PersistenceAdapter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@PersistenceAdapter
@RequiredArgsConstructor
@Slf4j
@Transactional
public class OrderPersistenceAdapter implements RegisterOrderPort, FindOrderPort, GetMemberOrderPort, RemoveOrderPort {

    private final OrderEntityRepository orderEntityRepository;

    private final ProductEntityRepository productEntityRepository;
    @Override
    public OrderEntity createOrder(OrderVo orderVo) {
        OrderEntity registerOrderEntity = OrderEntity.builder()
                .userId(orderVo.getUserId())
                .payment(orderVo.getPayment())
                .address(orderVo.getAddress())
                .phoneNumber(orderVo.getPhoneNumber())
                .createAt(orderVo.getCreateAt())
                .updateAt(orderVo.getUpdateAt())
                .status(orderVo.getStatus())
                .aggregateIdentifier(orderVo.getAggregateIdentifier())
                .build();

        OrderEntity createOrder = orderEntityRepository.save(registerOrderEntity);

        List<ProductEntity> registerProductList = new ArrayList<>();

        for(Product product : orderVo.getProductList()){
            ProductEntity registerProduct = ProductEntity.builder()
                    .productId(product.getProductId())
                    .productName(product.getProductName())
                    .sizeId(product.getSizeId())
                    .orderAmount(product.getOrderAmount())
                    .couponId(product.getCouponId())
                    .createAt(product.getCreateAt())
                    .updateAt(product.getUpdateAt())
                    .order(createOrder)
                    .build();

            createOrder.addProduct(registerProduct);
            registerProductList.add(registerProduct);
        }

       productEntityRepository.saveAll(registerProductList);

        return createOrder;
    }

    @Override
    public OrderEntity findOrder(OrderVo.OrderId orderId) {
        return orderEntityRepository.findById(orderId.getId())
                .orElseThrow(()-> new ErrorException(OrderErrorCode.ORDER_NOT_FOUND,"findOrder"));
    }

    @Override
    public List<OrderEntity> findOrderByMemberId(OrderVo.OrderProductUserId userId) {
        return orderEntityRepository.findByUserIdOrderByIdDesc(userId.getUserId());
    }

    @Override
    public Page<OrderEntity> findOrderByMemberIdPaging(OrderVo.OrderProductUserId userId, Pageable pageable) {
        return orderEntityRepository.findWithPagingByUserId(pageable,userId.getUserId());
    }


    @Override
    public List<OrderEntity> getMemberOrderPort(List<Long> memberIds) {
        return orderEntityRepository.findMemberOrderListByMemberIds(memberIds);
    }

    @Override
    public OrderEntity updateRemoveOrder(OrderVo.OrderId orderId,OrderVo.OrderStatus status) {

        OrderEntity orderEntity = findOrder(orderId);
        orderEntity.setStatus(status.getStatus());

        return orderEntityRepository.save(orderEntity);
    }
}
