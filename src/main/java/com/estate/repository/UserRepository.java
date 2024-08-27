package com.estate.repository;

import com.estate.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long>, JpaSpecificationExecutor<UserEntity> {

    Optional<UserEntity> findOneByUsernameAndStatus(String username, int status);

    List<UserEntity> findByStatusAndRoles_Code(Integer status, String code);

//    Optional<UserEntity> findOneByUsername(String username);

    Long countByIdIn(List<Long> ids);

    List<UserEntity> findByIdIn(List<Long> ids);

}
