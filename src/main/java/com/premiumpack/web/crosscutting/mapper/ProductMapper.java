package com.premiumpack.web.crosscutting.mapper;

import com.premiumpack.web.dataprovider.jpa.entity.ProductEntity;
import com.premiumpack.web.domain.ProductBase;
import com.premiumpack.web.domain.request.ProductRq;
import com.premiumpack.web.domain.request.ProductUpdateRq;
import com.premiumpack.web.domain.response.ProductRs;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    @Mapping(target = "uuid", expression = "java(java.util.UUID.randomUUID())")
    ProductEntity toProductEntity(ProductRq productRq);

    @Mapping(target = "idSupplier", source = "supplier.uuid")
    @Mapping(target = "nameSupplier", source = "supplier.name")
    ProductRs toProductRs(ProductEntity productEntity);
    ProductBase toProductBase(ProductEntity productEntity);
    void updateProductFromDto(ProductUpdateRq dto, @MappingTarget ProductEntity entity);


}
