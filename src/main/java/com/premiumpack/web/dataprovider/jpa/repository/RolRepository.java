package com.premiumpack.web.dataprovider.jpa.repository;

import com.premiumpack.web.dataprovider.jpa.entity.RolEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepository extends JpaRepository<RolEntity, Long> {

    RolEntity findByRolName(String rolName);
}
