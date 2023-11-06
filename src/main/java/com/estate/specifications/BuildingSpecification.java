package com.estate.specifications;

import com.estate.entity.*;
import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;


public final class BuildingSpecification extends GenericSpecification<BuildingEntity> {

    public Specification<BuildingEntity> byBuildingTypes(List<String> buildingTypes) {
        return (root, query, criteriaBuilder) -> {
            if (buildingTypes == null || buildingTypes.isEmpty()) return criteriaBuilder.conjunction();
            List<Predicate> predicates = new ArrayList<>();
            buildingTypes
                    .forEach(buildingType -> {
                        Predicate likeBuildingType = criteriaBuilder
                                .like(root.get(BuildingEntity_.BUILDING_TYPES), "%" + buildingType + "%");
                        predicates.add(likeBuildingType);
                    });
            return criteriaBuilder.or(predicates.toArray(new Predicate[0]));
        };
    }

    public Specification<BuildingEntity> byStaffId(Long staffId) {
        return (root, query, criteriaBuilder) -> {
            if (staffId == null) return criteriaBuilder.conjunction();
            Join<UserEntity, BuildingEntity> user = root.join(BuildingEntity_.USERS, JoinType.INNER);
            return criteriaBuilder.equal(user.get(UserEntity_.ID), staffId);
        };
    }

    public Specification<BuildingEntity> byRentArea(Double rentAreaFrom, Double rentAreaTo) {
        return (root, query, criteriaBuilder) -> {
            if (rentAreaFrom == null && rentAreaTo == null) return criteriaBuilder.conjunction();
            Subquery<RentAreaEntity> subQuery = query.subquery(RentAreaEntity.class);
            Root<RentAreaEntity> rentArea = subQuery.from(RentAreaEntity.class);
            List<Predicate> subPredicates = new ArrayList<>();
            Predicate equalBuildingId = criteriaBuilder
                    .equal(rentArea.get(RentAreaEntity_.BUILDING).get(BuildingEntity_.ID), root.get(BuildingEntity_.ID));
            subPredicates.add(equalBuildingId);
            if (rentAreaFrom != null) {
                Predicate gtEqualRentArea = criteriaBuilder.greaterThanOrEqualTo(rentArea.get(RentAreaEntity_.VALUE),
                        rentAreaFrom);
                subPredicates.add(gtEqualRentArea);
            }
            if (rentAreaTo != null) {
                Predicate ltEqualRentArea = criteriaBuilder.lessThanOrEqualTo(rentArea.get(RentAreaEntity_.VALUE),
                        rentAreaTo);
                subPredicates.add(ltEqualRentArea);
            }
            subQuery.select(rentArea).where(subPredicates.toArray(new Predicate[0]));
            return criteriaBuilder.exists(subQuery);
        };
    }


}
