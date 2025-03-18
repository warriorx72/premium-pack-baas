package com.premiumpack.web.dataprovider.jpa.entity;

import com.premiumpack.web.crosscutting.enumeration.OrderStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Types;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tb_orders")
public class OrderEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fi_id", nullable = false)
    private Long id;

    @Column(name = "fc_uuid" ,nullable = false)
    @JdbcTypeCode(Types.CHAR)
    private UUID uuid;

    @Column(name = "fd_date" ,nullable = false)
    private LocalDateTime date;

    @Column(name = "fc_customer_name" ,nullable = false, length = 100)
    private String customerName;

    @Column(name = "fc_phone" ,nullable = false, length = 10)
    private String phone;

    @Column(name = "fc_description" ,nullable = false)
    private String description;

    @Column(name = "fc_status" ,nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Column(name = "fi_total" ,nullable = false)
    private BigDecimal total;
}
