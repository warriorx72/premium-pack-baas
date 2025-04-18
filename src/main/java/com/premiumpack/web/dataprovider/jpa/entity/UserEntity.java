package com.premiumpack.web.dataprovider.jpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tb_security_users")
public class UserEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 2614479385669138123L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fi_id", nullable = false)
    private Long id;

    @Column(name = "fc_username" ,nullable = false, length = 100)
    private String userName;

    @Column(name = "fc_password", nullable = false)
    private String password;

    @Column(name = "fb_status", nullable = false)
    private Boolean status;

    @Column(name = "fc_email", nullable = false)
    private String email;

    @OneToMany(mappedBy = "user", cascade= CascadeType.ALL)
    private Set<UserRolEntity> usersRoles;
}