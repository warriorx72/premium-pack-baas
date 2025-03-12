package com.premiumpack.web.dataprovider.jpa.repository;

import com.premiumpack.web.dataprovider.jpa.entity.SupplierEntity;
import com.premiumpack.web.domain.response.SupplierRs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface SupplierRepository extends JpaRepository<SupplierEntity, Long> {

    Optional<SupplierEntity> findByUuid(UUID uuid);

    List<SupplierEntity> findByNameStartingWith(String name);

}
