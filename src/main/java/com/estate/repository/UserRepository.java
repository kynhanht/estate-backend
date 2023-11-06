package com.estate.repository;

import com.estate.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long>, JpaSpecificationExecutor<UserEntity> {

    Optional<UserEntity> findOneByUserNameAndStatus(String name, int status);

    List<UserEntity> findByStatusAndRoles_Code(Integer status, String code);

    Optional<UserEntity> findOneByUserName(String userName);

    Long countByIdIn(List<Long> ids);

    List<UserEntity> findByIdIn(List<Long> ids);

}
