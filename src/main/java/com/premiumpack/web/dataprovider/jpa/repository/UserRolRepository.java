package com.premiumpack.web.dataprovider.jpa.repository;

import com.premiumpack.web.dataprovider.jpa.entity.UserRolEntity;
import com.premiumpack.web.dataprovider.jpa.entity.UserRolKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRolRepository extends JpaRepository<UserRolEntity, UserRolKey> {
}
