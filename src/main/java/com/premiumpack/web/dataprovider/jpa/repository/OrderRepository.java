package com.premiumpack.web.dataprovider.jpa.repository;

import com.premiumpack.web.crosscutting.enumeration.OrderStatus;
import com.premiumpack.web.dataprovider.jpa.entity.OrderEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    Page<OrderEntity> findAllByStatus(Pageable pageable, OrderStatus status);
    Page<OrderEntity> findAllByStatusNot(Pageable pageable, OrderStatus status);
    Optional<OrderEntity> findByUuid(UUID uuid);

}
