package com.premiumpack.web.dataprovider.jpa.repository;

import com.premiumpack.web.dataprovider.jpa.entity.SupplierEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepository extends JpaRepository<SupplierEntity, Long> {
}
