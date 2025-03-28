package com.premiumpack.web.entrypoint.service.impl;

import com.premiumpack.web.crosscutting.enumeration.OrderStatus;
import com.premiumpack.web.crosscutting.mapper.OrderMapper;
import com.premiumpack.web.dataprovider.jpa.entity.OrderEntity;
import com.premiumpack.web.dataprovider.jpa.repository.OrderRepository;
import com.premiumpack.web.domain.request.OrderRq;
import com.premiumpack.web.domain.request.OrderUpdateRq;
import com.premiumpack.web.domain.response.OrderRs;
import com.premiumpack.web.entrypoint.exception.NotFoundException;
import com.premiumpack.web.entrypoint.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    @Override
    @Transactional
    public OrderRs addOrder(OrderRq request) {
        OrderEntity orderEntity = OrderMapper.INSTANCE.toOrderEntity(request);
        orderRepository.save(orderEntity);
        orderEntity.setIdText("ORD-" + String.format( "%05d", orderEntity.getId()));
        orderRepository.save(orderEntity);
        return OrderMapper.INSTANCE.toOrderRs(orderEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<OrderRs> getOrders(Pageable pageable) {
        Page<OrderEntity> orders = orderRepository.findAllByStatusNot(pageable, OrderStatus.COMPLETED);
        return orders.map(OrderMapper.INSTANCE::toOrderRs);
    }

    @Transactional(readOnly = true)
    @Override
    public Page<OrderRs> getSales(Pageable pageable) {
        Page<OrderEntity> orders = orderRepository.findAllByStatus(pageable, OrderStatus.COMPLETED);
        return orders.map(OrderMapper.INSTANCE::toOrderRs);
    }

    @Transactional
    @Override
    public OrderRs updateProduct(UUID uuid, OrderUpdateRq request) {
        OrderEntity orderEntity = orderRepository.findByUuid(uuid).orElseThrow(() -> new NotFoundException("Order not found"));
        OrderMapper.INSTANCE.updateOrderFromDto(request, orderEntity);
        orderRepository.save(orderEntity);
        return OrderMapper.INSTANCE.toOrderRs(orderEntity);
    }
}
