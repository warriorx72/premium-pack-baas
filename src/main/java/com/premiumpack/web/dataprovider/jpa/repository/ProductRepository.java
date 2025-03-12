package com.premiumpack.web.dataprovider.jpa.repository;
import com.premiumpack.web.dataprovider.jpa.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    Optional<ProductEntity> findByUuid(UUID uuid);

}
