package com.premiumpack.web.crosscutting.mapper;

import com.premiumpack.web.dataprovider.jpa.entity.SupplierEntity;
import com.premiumpack.web.domain.request.SupplierRq;
import com.premiumpack.web.domain.response.SupplierRs;
import com.premiumpack.web.domain.response.SupplierSavedRs;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SupplierMapper {
    SupplierMapper INSTANCE = Mappers.getMapper(SupplierMapper.class);

    @Mapping(target = "uuid", expression = "java(java.util.UUID.randomUUID())")
    SupplierEntity toSupplierEntity(SupplierRq supplierRq);

    SupplierSavedRs toSupplierSavedRs(SupplierEntity supplierEntity);

    SupplierRs toSupplierRs(SupplierEntity supplierEntity);
}
