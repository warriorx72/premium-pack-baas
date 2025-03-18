package com.premiumpack.web.dataprovider.jpa.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Types;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tb_products")
public class ProductEntity implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fi_id", nullable = false)
    private Long id;

    @Column(name = "fc_uuid" ,nullable = false)
    @JdbcTypeCode(Types.CHAR)
    private UUID uuid;

    @Column(name = "fc_name" ,nullable = false, length = 100)
    private String name;

    @Column(name = "fc_description" ,nullable = false, length = 200)
    private String description;

    @Column(name = "fi_quantity" ,nullable = false, length = 11)
    private Integer quantity;

    @Column(name = "fi_price" ,nullable = false, length = 11)
    private BigDecimal price;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "fi_id_supplier")
    private SupplierEntity supplier;

}
