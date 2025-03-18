package com.premiumpack.web.crosscutting.mapper;

import com.premiumpack.web.dataprovider.jpa.entity.SupplierEntity;
import com.premiumpack.web.domain.request.SupplierRq;
import com.premiumpack.web.domain.SupplierBased;
import com.premiumpack.web.domain.request.SupplierUpdateRq;
import com.premiumpack.web.domain.response.SupplierRs;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SupplierMapper {
    SupplierMapper INSTANCE = Mappers.getMapper(SupplierMapper.class);

    @Mapping(target = "uuid", expression = "java(java.util.UUID.randomUUID())")
    SupplierEntity toSupplierEntity(SupplierRq supplierRq);

    SupplierBased toSupplierSavedRs(SupplierEntity supplierEntity);

    SupplierRs toSupplierRs(SupplierEntity supplierEntity);

    List<SupplierRs> toSuppliersRs(List<SupplierEntity> supplierEntities);

    void updateSupplierFromDto(SupplierUpdateRq dto, @MappingTarget SupplierEntity entity);
}
