package com.premiumpack.web.entrypoint.controller;

import com.premiumpack.web.domain.request.OrderRq;
import com.premiumpack.web.domain.request.OrderUpdateRq;
import com.premiumpack.web.domain.response.OrderRs;
import com.premiumpack.web.entrypoint.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/order")
@CrossOrigin(origins = "*")
public class OrderController {

    private final OrderService orderService;
    @PostMapping
    public ResponseEntity<OrderRs> addOrder(@RequestBody @Valid OrderRq request) {
        return ResponseEntity.ok(orderService.addOrder(request));
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Page<OrderRs>> getProduct(Pageable pageable) {
        return ResponseEntity.ok(orderService.getOrders(pageable));
    }

    @GetMapping("/sales")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Page<OrderRs>> getSales(Pageable pageable) {
        return ResponseEntity.ok(orderService.getSales(pageable));
    }

    @PutMapping("/{uuid}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<OrderRs> updateOrder(@PathVariable UUID uuid, @RequestBody @Valid OrderUpdateRq request) {
        return ResponseEntity.ok(orderService.updateProduct(uuid, request));
    }

}
