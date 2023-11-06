package com.estate.specifications;

import com.estate.enums.SearchOperationEnum;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class SearchCriteria {

    private String key;
    private Object value;
    private SearchOperationEnum operation;

    public SearchCriteria(String key, Object value, SearchOperationEnum operation) {
        this.key = key;
        this.value = value;
        this.operation = operation;
    }


}
