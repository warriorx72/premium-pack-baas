package com.premiumpack.web.crosscutting.mapper;

import com.premiumpack.web.dataprovider.jpa.entity.ProductEntity;
import com.premiumpack.web.domain.request.ProductRq;
import com.premiumpack.web.domain.response.ProductRs;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    @Mapping(target = "uuid", expression = "java(java.util.UUID.randomUUID())")
    ProductEntity toProductEntity(ProductRq productRq);

    ProductRs toProductRs(ProductEntity productEntity);

}
