package com.premiumpack.web.dataprovider.jpa.repository;

import com.premiumpack.web.dataprovider.jpa.entity.UserRolEntity;
import com.premiumpack.web.dataprovider.jpa.entity.UserRolKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRolRepository extends JpaRepository<UserRolEntity, UserRolKey> {
}
