package com.premiumpack.web.entrypoint.service;

import com.premiumpack.web.domain.request.OrderRq;
import com.premiumpack.web.domain.request.OrderUpdateRq;
import com.premiumpack.web.domain.request.ProductUpdateRq;
import com.premiumpack.web.domain.response.OrderRs;
import com.premiumpack.web.domain.response.ProductRs;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface OrderService {

    OrderRs addOrder(OrderRq request);

    Page<OrderRs> getOrders(Pageable pageable);

    Page<OrderRs> getSales(Pageable pageable);

    OrderRs updateProduct(UUID uuid, OrderUpdateRq request);

}
