package com.premiumpack.web.dataprovider.jpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.Type;

import java.io.Serializable;
import java.sql.Types;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tb_suppliers")
public class SupplierEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fi_id", nullable = false)
    private Long id;

    @Column(name = "fc_uuid" ,nullable = false)
    @JdbcTypeCode(Types.CHAR)
    private UUID uuid;

    @Column(name = "fc_name" ,nullable = false, length = 100)
    private String name;

    @Column(name = "fc_address", nullable = false, length = 255)
    private String address;

    @Column(name = "fc_email", nullable = false, length = 100)
    @Email
    private String email;

    @Column(name = "fc_phone", nullable = false, length = 10)
    @Min(value = 10)
    private String phone;
}
