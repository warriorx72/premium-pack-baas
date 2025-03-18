package com.premiumpack.web.crosscutting.mapper;

import com.premiumpack.web.dataprovider.jpa.entity.OrderEntity;
import com.premiumpack.web.dataprovider.jpa.entity.SupplierEntity;
import com.premiumpack.web.domain.request.OrderRq;
import com.premiumpack.web.domain.request.OrderUpdateRq;
import com.premiumpack.web.domain.request.SupplierUpdateRq;
import com.premiumpack.web.domain.response.OrderRs;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderMapper {

    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    @Mapping(target = "uuid", expression = "java(java.util.UUID.randomUUID())")
    @Mapping(target = "status", expression = "java(com.premiumpack.web.crosscutting.enumeration.OrderStatus.PENDING)")
    @Mapping(target = "total", expression = "java(java.math.BigDecimal.ZERO)")
    @Mapping(target = "date", expression = "java(java.time.LocalDateTime.now())")
    OrderEntity toOrderEntity(OrderRq request);
    OrderRs toOrderRs(OrderEntity orderEntity);
    void updateOrderFromDto(OrderUpdateRq dto, @MappingTarget OrderEntity entity);

}
