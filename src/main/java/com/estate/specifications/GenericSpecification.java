package com.estate.specifications;

import com.estate.enums.SearchOperationEnum;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.jpa.domain.Specification;

public class GenericSpecification<T> {

    public Specification<T> byCommon(SearchCriteria criteria) {
        return (root, query, criteriaBuilder) -> {

            if (criteria.getValue() == null) {
                return criteriaBuilder.conjunction();
            } else {
                if (criteria.getValue().getClass().equals(String.class)) {
                    String value = (String) criteria.getValue();
                    if (StringUtils.isBlank(value)) {
                        return criteriaBuilder.conjunction();
                    }
                }
            }

            if (criteria.getOperation().equals(SearchOperationEnum.GREATER_THAN)) {
                return criteriaBuilder.greaterThan(
                        root.get(criteria.getKey()), criteria.getValue().toString());
            } else if (criteria.getOperation().equals(SearchOperationEnum.LESS_THAN)) {
                return criteriaBuilder.lessThan(
                        root.get(criteria.getKey()), criteria.getValue().toString());
            } else if (criteria.getOperation().equals(SearchOperationEnum.GREATER_THAN_EQUAL)) {
                return criteriaBuilder.greaterThanOrEqualTo(
                        root.get(criteria.getKey()), criteria.getValue().toString());
            } else if (criteria.getOperation().equals(SearchOperationEnum.LESS_THAN_EQUAL)) {
                return criteriaBuilder.lessThanOrEqualTo(
                        root.get(criteria.getKey()), criteria.getValue().toString());
            } else if (criteria.getOperation().equals(SearchOperationEnum.NOT_EQUAL)) {
                return criteriaBuilder.notEqual(
                        root.get(criteria.getKey()), criteria.getValue());
            } else if (criteria.getOperation().equals(SearchOperationEnum.EQUAL)) {
                return criteriaBuilder.equal(
                        root.get(criteria.getKey()), criteria.getValue());
            } else if (criteria.getOperation().equals(SearchOperationEnum.CONTAINING)) {
                return criteriaBuilder.like(
                        criteriaBuilder.lower(root.get(criteria.getKey())),
                        "%" + criteria.getValue().toString().toLowerCase() + "%");
            } else if (criteria.getOperation().equals(SearchOperationEnum.STARTING_WITH)) {
                return criteriaBuilder.like(
                        criteriaBuilder.lower(root.get(criteria.getKey())),
                        criteria.getValue().toString().toLowerCase() + "%");
            } else if (criteria.getOperation().equals(SearchOperationEnum.ENDING_WITH)) {
                return criteriaBuilder.like(
                        criteriaBuilder.lower(root.get(criteria.getKey())),
                        "%" + criteria.getValue().toString().toLowerCase());
            } else {
                return criteriaBuilder.conjunction();
            }
        };
    }

    public Specification<T> groupBy(String column) {
        return (root, query, criteriaBuilder) -> {
            if (StringUtils.isBlank(column)) return criteriaBuilder.conjunction();
            query.groupBy(root.get(column));
            return criteriaBuilder.conjunction();
        };
    }


    public Specification<T> orderBy(String column, String sortDirection) {
        return (root, query, criteriaBuilder) -> {
            if (!StringUtils.isBlank(column) && !StringUtils.isBlank(sortDirection)) {
                if (sortDirection.equals("desc")) {
                    query.orderBy(criteriaBuilder.desc(root.get(column)));
                } else {
                    query.orderBy(criteriaBuilder.asc(root.get(column)));
                }
            }
            return criteriaBuilder.conjunction();
        };
    }
}
