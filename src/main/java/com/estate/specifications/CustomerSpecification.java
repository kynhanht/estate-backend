package com.estate.specifications;

import com.estate.entity.CustomerEntity;
import com.estate.entity.CustomerEntity_;
import com.estate.entity.UserEntity;
import com.estate.entity.UserEntity_;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;


public class CustomerSpecification extends GenericSpecification<CustomerEntity> {


    public Specification<CustomerEntity> byStaffId(Long staffId) {
        return (root, query, criteriaBuilder) -> {
            if (staffId == null) return criteriaBuilder.conjunction();
            Join<UserEntity, CustomerEntity> user = root.join(CustomerEntity_.USERS, JoinType.INNER);
            return criteriaBuilder.equal(user.get(UserEntity_.ID), staffId);
        };
    }
}
