package com.premiumpack.web.dataprovider.jpa.repository;

import com.premiumpack.web.dataprovider.jpa.entity.RolEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolRepository extends JpaRepository<RolEntity, Long> {

    RolEntity findByRolName(String rolName);
}
