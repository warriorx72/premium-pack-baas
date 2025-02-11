package com.premiumpack.web.crosscutting.mapper;

import com.premiumpack.web.dataprovider.jpa.entity.UserEntity;
import com.premiumpack.web.dataprovider.jpa.entity.UserRolEntity;
import com.premiumpack.web.domain.response.SignUpRs;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SignUpRsMapper {

    SignUpRsMapper INSTANCE = Mappers.getMapper(SignUpRsMapper.class);

    @Mapping(source = "user.userName", target = "userName")
    @Mapping(source = "user.status", target = "isActive")
    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(source = "userRoles", target = "roles", qualifiedByName = "userRolesToListString")
    SignUpRs toSignUpRs(UserEntity user, List<UserRolEntity> userRoles);

    @Named("userRolesToListString")
    public static List<String> userRolesToListString(List<UserRolEntity> userRoles) {
        return userRoles.stream().map(r -> r.getRol().getRolName()).toList();
    }
}
